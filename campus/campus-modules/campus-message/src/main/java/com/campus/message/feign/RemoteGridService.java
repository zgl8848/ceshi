package com.campus.message.feign;

import com.campus.common.core.constant.SecurityConstants;
import com.campus.common.core.constant.ServiceNameConstants;
import com.campus.common.core.util.R;
import com.campus.grid.api.entity.equipmententity.EquipmentMsgSynchronize;
import com.campus.message.feign.factory.RemoteGridServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


/**
 * @author campus
 */
@FeignClient(value = ServiceNameConstants.GRID_SERVICE, fallbackFactory = RemoteGridServiceFallbackFactory.class)
@Service
public interface RemoteGridService {
	/**
	 * 存储学校设备信息
	 *
	 * @param equipment 存放Equipment集合
	 * @param from      表示为内部调用请求
	 * @return R
	 */
	@PostMapping(value = "/equipment/saveEquipmentMsg")
	R saveEquipmentMsg(@RequestBody EquipmentMsgSynchronize equipment, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 根据学习编码获取学校ID
	 *
	 * @param schoolCode 存放Equipment集合
	 * @param from       表示为内部调用请求
	 * @return R
	 */
	@GetMapping(value = "/equipment/getSchoolIdByCode/{schoolCode}")
	String getSchoolIdByCode(@PathVariable("schoolCode") String schoolCode, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 根据设备id以及学校id更新设备信息
	 *
	 * @param equipment
	 * @param from      表示为内部调用请求
	 * @return R
	 */
	@PostMapping(value = "/equipment/updateEquipmentMsg")
	R updateEquipmentMsg(@RequestBody EquipmentMsgSynchronize equipment, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 根据设备id以及学校id删除设备数据
	 *
	 * @param equipment
	 * @param from     表示为内部调用请求
	 * @return R
	 */
	@PostMapping(value = "/equipment/deleteEquipmentMsg")
	R deleteEquipmentMsg(@RequestBody EquipmentMsgSynchronize equipment, @RequestHeader(SecurityConstants.FROM) String from);

}

