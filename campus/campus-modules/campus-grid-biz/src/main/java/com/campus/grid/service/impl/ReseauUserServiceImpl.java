package com.campus.grid.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.admin.api.dto.DictInfo;
import com.campus.admin.api.feign.RemoteDictService;
import com.campus.grid.api.entity.ReseauUser;
import com.campus.grid.api.entity.ReseauVo;
import com.campus.grid.mapper.ReseauUserMapper;
import com.campus.grid.service.ReseauUserService;

import lombok.AllArgsConstructor;

/**
 * @author hu
 * @date 2019-01-02 16:17:17
 */
@Service
@AllArgsConstructor
public class ReseauUserServiceImpl extends ServiceImpl<ReseauUserMapper, ReseauUser> implements ReseauUserService {

	private ReseauUserMapper reseauUserMapper;

	private final RemoteDictService remoteDictService;

	@Override
	public void editReseauUser(ReseauVo reseauVo) {
		//-------------------修改网格负责人--------------------------
		String strInspectStartDate = reseauVo.getStrInspectStartDate();
		String strFireInspectStartDate = reseauVo.getStrFireInspectStartDate();
		LocalDate DateTime = LocalDate.parse(strInspectStartDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate fireDateTime = LocalDate.parse(strFireInspectStartDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		reseauVo.setInspectStartDate(DateTime);
		reseauVo.setFireInspectStartDate(fireDateTime);
		reseauUserMapper.editReseauUser(reseauVo);
		reseauUserMapper.editFireReseauUser(reseauVo);
	}

	@Override
	public void updateRemark(ReseauVo reseauVo){
		reseauUserMapper.updateRemark(reseauVo.getRemark(), reseauVo.getReseauId(), reseauVo.getResponsiblyType());
	}

	@Override
	public String getInfo(String reseauId, Integer type){
		String remark = reseauUserMapper.getInfo(reseauId, type);
		if (remark==null || "".equals(remark)){
			DictInfo dictInfo = remoteDictService.info("duty_type", type+"");//duty_type 网格职责类型（字典）
			if (dictInfo!=null){
				remark = dictInfo.getRemarks();
			}
		}
		return remark;
	}
}
