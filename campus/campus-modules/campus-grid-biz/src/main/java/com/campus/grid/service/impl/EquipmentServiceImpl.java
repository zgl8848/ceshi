package com.campus.grid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.grid.api.dto.EquipmentDTO;
import com.campus.grid.api.dto.PageBean;
import com.campus.grid.api.entity.equipmententity.Equipment;
import com.campus.grid.mapper.EquipmentMapper;
import com.campus.grid.service.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author hu
 * @date 2019-01-02 16:20:11
 */
@Service
@AllArgsConstructor
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements EquipmentService {

	@Autowired
	private SchoolServiceImpl schoolService;

	private EquipmentMapper equipmentMapper;

	@Override
	public PageBean queryPage(Integer currentPage, Integer size, String equipmentName, String schoolId) {
		int total = equipmentMapper.getTotal(equipmentName, schoolId);
		Integer startIndex = (currentPage - 1) * size;
		List<Equipment> equipmentList = equipmentMapper.queryPage(startIndex, size, equipmentName, schoolId);
		List<EquipmentDTO> EquipmentDTOList = new ArrayList<>();
		if (equipmentList != null && equipmentList.size() > 0) {
			for (Equipment e : equipmentList) {
				EquipmentDTO equipmentDTO = new EquipmentDTO(e);
				equipmentDTO.setSchoolName(schoolService.getById(e.getSchoolId()).getSchoolName());
				EquipmentDTOList.add(equipmentDTO);
			}
		}
		PageBean pageBean = new PageBean();
		pageBean.setCurrent(currentPage);
		double ceil = Math.ceil((total * 1.0) / size);
		int pages = (int) ceil;
		pageBean.setSize(size);
		pageBean.setPages(pages);
		pageBean.setTotal(total);
		pageBean.setRecords(EquipmentDTOList);
		return pageBean;
	}

	@Override
	public String getSchoolIdByCode(String schoolCode) {
		return equipmentMapper.getSchoolIdByCode(schoolCode);
	}

	@Override
	public boolean updateBySchoolIdAndEquId(Equipment equipment) {
		return equipmentMapper.updateBySchoolIdAndEquId(equipment);
	}

	@Override
	public boolean deleteEquipmentMsg(String schoolId, String equId) {
		return equipmentMapper.deleteBatchEquipmentMsg(schoolId, equId);
	}
}
