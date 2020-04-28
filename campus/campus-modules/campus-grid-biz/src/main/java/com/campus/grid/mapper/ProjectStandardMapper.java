package com.campus.grid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.grid.api.entity.ProjectStandard;
import com.campus.grid.api.entity.TaskResult;

import java.util.*;
/**
 * 
 *
 * @author wang_h
 * @date 2019-06-20 15:30:14
 */
public interface ProjectStandardMapper extends BaseMapper<ProjectStandard> {
	List<TaskResult> selectTaskResult(String ProjectId);
	void deleteaskResultOfStandardId(String standardId);
	void depletionInNumbers(String ProjectId);
	void addNumbers(String ProjectId);
}
