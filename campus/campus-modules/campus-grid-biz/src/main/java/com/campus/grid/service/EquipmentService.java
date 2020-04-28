package com.campus.grid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.grid.api.dto.PageBean;
import com.campus.grid.api.entity.equipmententity.Equipment;

import java.util.Date;

/**
 * @author hu
 * @date 2019-01-02 16:20:11
 */
public interface EquipmentService extends IService<Equipment> {
	String getSchoolIdByCode(String schoolCode);

	boolean updateBySchoolIdAndEquId(Equipment equipment);

	boolean deleteEquipmentMsg(String schoolId, String equId);

	PageBean queryPage(Integer current, Integer size, String equipmentName, String schoolId);

}
