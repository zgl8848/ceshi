package com.campus.grid.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.common.core.constant.SecurityConstants;
import com.campus.common.core.util.R;
import com.campus.common.minio.service.MinioTemplate;
import com.campus.common.security.annotation.Inner;
import com.campus.grid.api.dto.PageBean;
import com.campus.grid.api.entity.equipmententity.Equipment;
import com.campus.grid.api.entity.equipmententity.EquipmentMsgSynchronize;
import com.campus.grid.service.EquipmentImgService;
import com.campus.grid.service.EquipmentService;
import com.campus.grid.util.MessageConstants;
import com.campus.grid.util.MessageMethods;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 设备信息
 *
 * @author hu
 * @date 2019-01-02 16:20:11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/equipment")
@Slf4j
public class EquipmentController {
	private final EquipmentService equipmentService;
	private final EquipmentImgService equipmentImgService;
	private static MessageMethods messageMethods=new MessageMethods();
	private final MinioTemplate minioTemplate;
	private Gson gson ;

	/**
	 * 按条件查询
	 *
	 * @param current
	 * @param size
	 * @param equipmentName
	 * @param schoolId
	 * @return
	 */
	@GetMapping("/queryPage")
	public R getQueryPage(Integer current, Integer size, String equipmentName, String schoolId) {
		PageBean pageBean = equipmentService.queryPage(current, size, equipmentName, schoolId);
		return new R<>(pageBean);
	}

	/**
	 * 根据学校id获取所以设备信息
	 */
	@GetMapping("/page")
	public R getSchoolEquipmentMsg(Page page, @RequestParam("schoolId") String schoolId) {
		return new R<>(equipmentService.page(page, Wrappers.query(new Equipment()).eq(Equipment::getSchoolId, schoolId)));
	}

	/**
	 * 设备图片保存接口
	 *
	 * @param schoolId 学校id
	 * @param imgNames 图片名字
	 * @param ip       ip地址
	 */
	@GetMapping("imgSave")
	public R saveEquipmentImg(@RequestParam("schoolId") String schoolId,
							  @RequestParam("imgNames") String imgNames,
							  @RequestParam("ip") String ip) {
		return new R<>(equipmentImgService.save(schoolId, imgNames, ip));
	}

	/**
	 * 存储学校设备信息
	 *
	 * @param equipmentMsgSynchronize 设备对象
	 */
//	@Inner
	@PostMapping("/saveEquipment")
	public R saveEquipmentMsg(@RequestBody EquipmentMsgSynchronize equipmentMsgSynchronize) {
		//解析消息转换为设备信息实体类
		Equipment equipment = gson.fromJson(gson.toJson(equipmentMsgSynchronize), Equipment.class);
		//根据学校编号（固定编号）查询到schoolId并添加
		String schoolId = equipmentService.getSchoolIdByCode(equipmentMsgSynchronize.getSchoolCode());
		equipment.setSchoolId(schoolId);
		equipment.setCreateTime(new Date());
		try {
//			判断是否有图片
			if(StringUtils.isNotBlank(equipment.getImgUrl())){
//			获得正确格式的设备base64编码  data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEASABIAAD/   取逗号后边
				String[] imgUrl = equipment.getImgUrl().split(",");
				//设备信息存入db
				String picUrl = messageMethods.saveToFile(imgUrl[1], minioTemplate, MessageConstants.BASE64_PIC);
				equipment.setImgUrl(MessageConstants.PIC_PREFIX + picUrl);
			}
				return new R(equipmentService.saveOrUpdate(equipment),"添加设备信息成功");
			} catch (Exception e) {
				log.error("add操作数据库失败", e);
				return new R(false,"添加设备信息失败");
			}
	}

	/**
	 * 根据学校编号获取学校id
	 *
	 * @param schoolCode 学校编码
	 */
	@Inner
	@GetMapping("/getSchoolIdByCode/{schoolCode}")
	public String getSchoolIdByCode(@PathVariable("schoolCode") String schoolCode) {
		return equipmentService.getSchoolIdByCode(schoolCode);
	}

	/**
	 * 更新学校设备信息
	 *
	 * @param equipmentMsgSynchronize 存储设备对象的集合
	 * @return R
	 */
//	@Inner
	@PostMapping("/updateEquipment")
	public R updateEquipmentMsg(@RequestBody EquipmentMsgSynchronize equipmentMsgSynchronize) {
		//解析消息转换为设备信息实体类
		Equipment equipment = gson.fromJson(gson.toJson(equipmentMsgSynchronize), Equipment.class);
		try {
		//根据学校编号（固定编号）查询到schoolId并添加
		String schoolId = equipmentService.getSchoolIdByCode(equipmentMsgSynchronize.getSchoolCode());
		equipment.setSchoolId(schoolId);
//			判断是否有图片
			if(StringUtils.isNotBlank(equipment.getImgUrl())){
//			获得正确格式的设备base64编码  data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEASABIAAD/   取逗号后边
				String[] imgUrl = equipment.getImgUrl().split(",");
				//设备信息存入db
				String picUrl = messageMethods.saveToFile(imgUrl[1], minioTemplate, MessageConstants.BASE64_PIC);
				equipment.setImgUrl(MessageConstants.PIC_PREFIX + picUrl);
			}
			return new R(equipmentService.updateBySchoolIdAndEquId(equipment),"更新设备成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new R(false,"更新设备失败");
		}
	}

	/**
	 * 删除学校设备信息
	 * @return R
	 */
//	@Inner
	@PostMapping("/deleteEquipment")
	public R deleteEquipmentMsg(@RequestBody EquipmentMsgSynchronize equipmentMsgSynchronize){
		//解析消息转换为设备信息实体类
		Equipment equipment = gson.fromJson(gson.toJson(equipmentMsgSynchronize), Equipment.class);
		//根据学校编号（固定编号）查询到schoolId并添加
		String schoolId = null;
		try {
			schoolId = equipmentService.getSchoolIdByCode(equipmentMsgSynchronize.getSchoolCode());
			return new R(equipmentService.deleteEquipmentMsg(schoolId, equipment.getEquipmentId()),"删除设备成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new R(false,"删除设备失败");
		}
	}

}
