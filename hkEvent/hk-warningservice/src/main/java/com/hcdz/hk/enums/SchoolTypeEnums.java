package com.hcdz.hk.enums;

public enum SchoolTypeEnums {

	YXYEY("ea0de7bdff0a4498b0fe26e849388827","00000001","附小幼儿园"),
	AAA("9b33f1c788f04dceb665fa15cfcd8ed6","00000002","aaaa"),
	FSYZ("44c21fea0001459ab40bfa4842026dc6","00000010","佛山一中");
	
	private String schoolIndex;
	
	private String schoolCode;
	
	private String schoolName;

	public String getSchoolIndex() {
		return schoolIndex;
	}
	public void setSchoolIndex(String schoolIndex) {
		this.schoolIndex = schoolIndex;
	}

	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	private SchoolTypeEnums(String schoolIndex, String schoolCode, String schoolName) {
		this.schoolIndex = schoolIndex;
		this.schoolCode = schoolCode;
		this.schoolName = schoolName;
	}
	
	/**
	 * 通过schoolndex得到学校code
	 * @param schoolIndex
	 * @return
	 */
	public static String getSchoolCode(String schoolIndex) {
		for(SchoolTypeEnums st:values()) {
			if(st.getSchoolIndex().equals(schoolIndex)) {
				return st.getSchoolCode();
			}
		}
		return null;
	}
	
}
