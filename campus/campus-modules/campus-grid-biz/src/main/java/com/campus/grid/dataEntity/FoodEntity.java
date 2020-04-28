package com.campus.grid.dataEntity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix="food")
public class FoodEntity {
	public List<Map>  datalist;
	public String mapprovince;
	public String citycode;

	public Map  sampledata;

	public List<Map> rydata;

	public List<Map> certlists;

	public List<Map> trendlist;

	public List<Map> getDatalist() {
		return datalist;
	}

	public String getMapprovince() {
		return mapprovince;
	}

	public String getCitycode() {
		return citycode;
	}

	public Map getSampledata() {
		return sampledata;
	}

	public List<Map> getRydata() {
		return rydata;
	}

	public List<Map> getCertlists() {
		return certlists;
	}

	public List<Map> getTrendlist() {
		return trendlist;
	}

	public void setDatalist(List<Map> datalist) {
		this.datalist = datalist;
	}

	public void setMapprovince(String mapprovince) {
		this.mapprovince = mapprovince;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public void setSampledata(Map sampledata) {
		this.sampledata = sampledata;
	}

	public void setRydata(List<Map> rydata) {
		this.rydata = rydata;
	}

	public void setCertlists(List<Map> certlists) {
		this.certlists = certlists;
	}

	public void setTrendlist(List<Map> trendlist) {
		this.trendlist = trendlist;
	}
}
