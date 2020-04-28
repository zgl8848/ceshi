package com.campus.grid.mapper;

import com.campus.grid.api.entity.ReseauSchoolBus;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReseauSchoolBusMapper {

    /**
     * 查询是否存在该校车
     * @param carName
     * @return
     */
    @Select("SELECT car_reseau_id FROM grid_reseau_bus WHERE car_name=#{carName}")
    String isBusId(@Param("carName") String carName);

    boolean addSchoolBus(ReseauSchoolBus reseauSchoolBus);

    String isCheckName(ReseauSchoolBus reseauSchoolBus);

    String isReseauByCarReseauId(String carReseauId);

    boolean updateCheckName(ReseauSchoolBus reseauSchoolBus);

    String isCarId(ReseauSchoolBus reseauSchoolBus);

    boolean updateSchoolBusByCarId(String carId);

    boolean updateCarReseauId(String carReseauId);

    List<ReseauSchoolBus> getCarName(@Param("schoolId") String schoolId, @Param("topType") String topType, @Param("carId") String carId);
}
