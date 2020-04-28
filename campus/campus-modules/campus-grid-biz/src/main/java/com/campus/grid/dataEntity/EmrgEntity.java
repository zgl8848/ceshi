package com.campus.grid.dataEntity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "emrg")
public class EmrgEntity {
	public List<Map> effectlists;

	public List<Map> numberlists;

	public List<Map> classlists;

	public Map overall;

	public String province_code;
	public String city_code;
	public List<Map> listdata;

	public List<Map> rankinglists;

	public void setEffectlists(List<Map> effectlists) {
		this.effectlists = effectlists;
	}

	public void setNumberlists(List<Map> numberlists) {
		this.numberlists = numberlists;
	}

	public void setClasslists(List<Map> classlists) {
		this.classlists = classlists;
	}

	public void setOverall(Map overall) {
		this.overall = overall;
	}

	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public void setListdata(List<Map> listdata) {
		this.listdata = listdata;
	}

	public List<Map> getEffectlists() {
		return effectlists;
	}

	public List<Map> getNumberlists() {
		return numberlists;
	}

	public List<Map> getClasslists() {
		return classlists;
	}

	public Map getOverall() {
		return overall;
	}

	public String getProvince_code() {
		return province_code;
	}

	public String getCity_code() {
		return city_code;
	}

	public List<Map> getListdata() {
		return listdata;
	}

	public List<Map> getRankinglists() {
		return rankinglists;
	}

	public void setRankinglists(List<Map> rankinglists) {
		this.rankinglists = rankinglists;
	}
}
