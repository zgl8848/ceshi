package com.campus.grid.api.dto;

import java.io.Serializable;
import java.util.List;

public class FunctionTypeDTO implements Serializable {
	private String id;
	private String name;
	private String functionTypeName;
	private List<String> functionTypeValue;
	private List<FunctionTypeDTO> children;

	public FunctionTypeDTO() {
	}

	public FunctionTypeDTO(String id, String name, String functionTypeName, List<String> functionTypeValue, List<FunctionTypeDTO> children) {
		this.id = id;
		this.name = name;
		this.functionTypeName = functionTypeName;
		this.functionTypeValue = functionTypeValue;
		this.children = children;
	}

	@Override
	public String toString() {
		return "FunctionTypeDTO{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", functionTypeName='" + functionTypeName + '\'' +
				", functionTypeValue=" + functionTypeValue +
				", children=" + children +
				'}';
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFunctionTypeName() {
		return functionTypeName;
	}

	public void setFunctionTypeName(String functionTypeName) {
		this.functionTypeName = functionTypeName;
	}

	public List<String> getFunctionTypeValue() {
		return functionTypeValue;
	}

	public void setFunctionTypeValue(List<String> functionTypeValue) {
		this.functionTypeValue = functionTypeValue;
	}

	public List<FunctionTypeDTO> getChildren() {
		return children;
	}

	public void setChildren(List<FunctionTypeDTO> children) {
		this.children = children;
	}
}
