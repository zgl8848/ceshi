package com.campus.grid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.grid.api.entity.ReseauUser;
import com.campus.grid.api.entity.ReseauVo;

/**
 * @author hu
 * @date 2019-01-02 16:17:17
 */
public interface ReseauUserService extends IService<ReseauUser> {

	//-------------------修改网格负责人--------------------------
   	void editReseauUser(ReseauVo reseauVo);

    void updateRemark(ReseauVo reseauVo);

	/**
	 * 获取网格职责信息
	 * @param reseauId
	 * @return
	 */
	String getInfo(String reseauId, Integer type);
}
