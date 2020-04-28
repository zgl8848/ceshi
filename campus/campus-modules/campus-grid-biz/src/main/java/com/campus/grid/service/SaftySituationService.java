package com.campus.grid.service;

import com.campus.grid.api.entity.saftysituation.HiddenDangerInfo;
import com.campus.grid.api.entity.saftysituation.InspectInfo;

public interface SaftySituationService {
	HiddenDangerInfo getHiddenDanger();

	InspectInfo getInspect();
}
