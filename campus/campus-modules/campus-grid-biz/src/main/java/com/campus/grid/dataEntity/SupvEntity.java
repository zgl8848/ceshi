package com.campus.grid.dataEntity;

import com.campus.grid.api.entity.saftysupervise.Classify;
import com.campus.grid.api.entity.saftysupervise.EveryData;
import com.campus.grid.api.entity.saftysupervise.MapData;
import com.campus.grid.api.entity.saftysupervise.OverAll;
import com.campus.grid.api.entity.saftysupervise.TrendChart;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "supv")
public class SupvEntity {
	public String province_code;
	public String city_code;
	public List<MapData>  supvlists;

	public OverAll supvoa;

	public List<Classify>  supvclassifylist;

	public List<EveryData>  charteverydata;
	public List<TrendChart>  chartlists;

	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public void setSupvlists(List<MapData> supvlists) {
		this.supvlists = supvlists;
	}

	public void setSupvoa(OverAll supvoa) {
		this.supvoa = supvoa;
	}

	public void setSupvclassifylist(List<Classify> supvclassifylist) {
		this.supvclassifylist = supvclassifylist;
	}

	public void setCharteverydata(List<EveryData> charteverydata) {
		this.charteverydata = charteverydata;
	}

	public void setChartlists(List<TrendChart> chartlists) {
		this.chartlists = chartlists;
	}

	public String getProvince_code() {
		return province_code;
	}

	public String getCity_code() {
		return city_code;
	}

	public List<MapData> getSupvlists() {
		return supvlists;
	}

	public OverAll getSupvoa() {
		return supvoa;
	}

	public List<Classify> getSupvclassifylist() {
		return supvclassifylist;
	}

	public List<EveryData> getCharteverydata() {
		return charteverydata;
	}

	public List<TrendChart> getChartlists() {
		return chartlists;
	}
}
