package com.campus.grid.api.dto;

import java.io.Serializable;

public class GridFunctionDTO implements Serializable {
	private String gridFunctionParent;
	private String gridFunction;
	private String buildingName;
	private String spaceName;
	private String spaceType;
	private String inspectMode;

	public GridFunctionDTO() {
	}

	public GridFunctionDTO(String gridFunctionParent, String gridFunction, String buildingName, String spaceName, String spaceType, String inspectMode) {
		this.gridFunctionParent = gridFunctionParent;
		this.gridFunction = gridFunction;
		this.buildingName = buildingName;
		this.spaceName = spaceName;
		this.spaceType = spaceType;
		this.inspectMode = inspectMode;
	}

	@Override
	public String toString() {
		return "GridFunctionDTO{" +
				"gridFunctionParent='" + gridFunctionParent + '\'' +
				", gridFunction='" + gridFunction + '\'' +
				", buildingName='" + buildingName + '\'' +
				", spaceName='" + spaceName + '\'' +
				", spaceType='" + spaceType + '\'' +
				", inspectMode='" + inspectMode + '\'' +
				'}';
	}

	public String getGridFunctionParent() {
		return gridFunctionParent;
	}

	public void setGridFunctionParent(String gridFunctionParent) {
		this.gridFunctionParent = gridFunctionParent;
	}

	public String getGridFunction() {
		return gridFunction;
	}

	public void setGridFunction(String gridFunction) {
		this.gridFunction = gridFunction;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public String getSpaceType() {
		return spaceType;
	}

	public void setSpaceType(String spaceType) {
		this.spaceType = spaceType;
	}

	public String getInspectMode() {
		return inspectMode;
	}

	public void setInspectMode(String inspectMode) {
		this.inspectMode = inspectMode;
	}
}
