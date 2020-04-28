package com.campus.grid.service;

import com.campus.grid.api.entity.ReseauSchoolBus;

import java.util.List;

//网格划分-校车设备
public interface ReseauSchoolBusService {
    boolean isBusId(String carName);

    boolean addSchoolBus(ReseauSchoolBus reseauSchoolBus);

    boolean updateCheckName(ReseauSchoolBus reseauSchoolBus);

    boolean deleteSchoolBusByCarId(String carId);

    boolean deleteCarReseauId(String carReseauId);

    List<ReseauSchoolBus> getCarName(String schoolId, String topType, String carId);
}
