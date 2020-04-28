package com.campus.grid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.grid.api.entity.equipmententity.EquipmentImg;

/**
 * @author eatheryu
 */
public interface EquipmentImgService extends IService<EquipmentImg> {
	boolean save(String schoolId, String imgNames, String ip);
}
