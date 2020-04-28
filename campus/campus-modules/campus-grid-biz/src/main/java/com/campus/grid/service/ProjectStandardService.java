package com.campus.grid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.grid.api.entity.ProjectStandard;

/**
 * 
 *
 * @author wang_h
 * @date 2019-06-20 15:30:14
 */
public interface ProjectStandardService extends IService<ProjectStandard> {
	boolean save(String projectId,String content);
	boolean removeById(String standardId,String projectId);
}
