package com.campus.grid.mapper;

import com.campus.grid.api.dto.FunctionTypeDTO;
import com.campus.grid.api.dto.FunctionTypeManageDTO;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FunctionTypeManageMapper {
	//根据问题类型id查询问题类型子类型
	List<FunctionTypeDTO> getFunctionChildren(String functionId);

	//查询问题子类型
	List<FunctionTypeManageDTO> getList(@Param("isParent") String isParent, @Param("functionId") String functionId);

	//根据id查询物品所属的类型
	String getParentIdOfFunction(String functionId);

	//查询问题类型标签
	String findFunctionLabel(String FunctionId);

	//删除问题标签下字标签数据
	void setChildrenOfFunctionType(@Param("functionId") String functionId, @Param("values") String values);

	//插入问题类型
	void insertFunction(@Param("id") String id, @Param("functionName") String functionName, @Param("time") LocalDateTime time);

	//插入问题类型标签
	void insertFunctionLabel(@Param("id") String id, @Param("parentId") String parentId, @Param("functionName") String functionName, @Param("time") LocalDateTime time);

	//修改问题类型或类型标签的名字
	void updateFunctionName(@Param("functionId") String functionId, @Param("functionName") String functionName);

	//查询问题类型下所有的问题标签id
	List<String> selectFunctionLabel(String functionId);

	//删除问题类型或问题标签
	void delFunction(String functionId);

	//删除问题类型下所有的问题标签
	void delFunctionLabel(List<String> list);
}
