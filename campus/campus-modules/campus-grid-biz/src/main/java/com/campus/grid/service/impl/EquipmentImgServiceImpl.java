package com.campus.grid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.grid.api.entity.equipmententity.EquipmentImg;
import com.campus.grid.mapper.EquipmentImgMapper;
import com.campus.grid.service.EquipmentImgService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * @author hu
 * @date 2019-01-02 16:20:11
 */
@Service
@AllArgsConstructor
public class EquipmentImgServiceImpl extends ServiceImpl<EquipmentImgMapper, EquipmentImg> implements EquipmentImgService {
	private EquipmentImgMapper equipmentImgMapper;

	@Override
	public boolean save(String schoolId, String imgNames, String ip) {
		EquipmentImg equipmentImg = new EquipmentImg();
		equipmentImg.setIp(ip);
		equipmentImg.setPicUrl(imgNames);
		equipmentImg.setSchoolId(schoolId);
		return equipmentImgMapper.save(equipmentImg);
	}

}
