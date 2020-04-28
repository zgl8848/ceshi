package com.campus.grid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.grid.api.entity.equipmententity.Equipment;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author hu
 * @date 2019-01-02 16:20:11
 */
public interface EquipmentMapper extends BaseMapper<Equipment> {
	String getSchoolIdByCode(String schoolCode);

	boolean updateBySchoolIdAndEquId(Equipment equipment);

	boolean deleteBatchEquipmentMsg(@Param("schoolId") String schoolId, @Param("equId") String equId);

	int getTotal(@Param("equipmentName") String equipmentName, @Param("schoolId") String schoolId);

	List<Equipment> queryPage(@Param("startIndex") Integer startIndex, @Param("size") Integer size, @Param("equipmentName") String equipmentName, @Param("schoolId") String schoolId);
}
