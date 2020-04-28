package com.campus.grid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.grid.api.entity.equipmententity.EquipmentImg;

public interface EquipmentImgMapper extends BaseMapper<EquipmentImg> {
	boolean save(EquipmentImg equipment);
}
