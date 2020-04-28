package com.campus.grid.dataEntity;

import com.campus.grid.api.entity.saftysituation.CheckStand;
import com.campus.grid.api.entity.saftysituation.EduLearnNum;
import com.campus.grid.api.entity.saftysituation.MapData;
import com.campus.grid.api.entity.saftysituation.SchoolRank;
import com.campus.grid.api.entity.saftysituation.VisitorInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "safty")
public class SaftyEntity {
	public List<SchoolRank> rankinglists;

	public String mapprovince_code;
	public String mapcity_code;
	public List<MapData> maplists;

	public List<EduLearnNum>  edulists;

	public List<CheckStand> atndlists;

	public VisitorInfo  visitor;

	public Map monitordata;

	public void setRankinglists(List<SchoolRank> rankinglists) {
		this.rankinglists = rankinglists;
	}

	public void setMapprovince_code(String mapprovince_code) {
		this.mapprovince_code = mapprovince_code;
	}

	public void setMapcity_code(String mapcity_code) {
		this.mapcity_code = mapcity_code;
	}

	public void setMaplists(List<MapData> maplists) {
		this.maplists = maplists;
	}

	public void setEdulists(List<EduLearnNum> edulists) {
		this.edulists = edulists;
	}

	public void setAtndlists(List<CheckStand> atndlists) {
		this.atndlists = atndlists;
	}

	public void setVisitor(VisitorInfo visitor) {
		this.visitor = visitor;
	}

	public void setMonitordata(Map monitordata) {
		this.monitordata = monitordata;
	}

	public List<SchoolRank> getRankinglists() {
		return rankinglists;
	}

	public String getMapprovince_code() {
		return mapprovince_code;
	}

	public String getMapcity_code() {
		return mapcity_code;
	}

	public List<MapData> getMaplists() {
		return maplists;
	}

	public List<EduLearnNum> getEdulists() {
		return edulists;
	}

	public List<CheckStand> getAtndlists() {
		return atndlists;
	}

	public VisitorInfo getVisitor() {
		return visitor;
	}

	public Map getMonitordata() {
		return monitordata;
	}
}
