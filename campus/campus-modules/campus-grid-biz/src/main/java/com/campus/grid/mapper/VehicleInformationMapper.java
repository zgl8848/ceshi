package com.campus.grid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campus.grid.api.entity.VehicleInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VehicleInformationMapper extends BaseMapper<VehicleInformation> {

	List<VehicleInformation> queryPage(@Param("startIndex")Long startIndex, @Param("size")Long size,@Param("query")VehicleInformation vehicleInformation);

	int getTotal();

//	List<VehicleInformation> listVehicleInformationByxx(@Param("xx")String xx);

	void upedateVehicleInformation(@Param("query")VehicleInformation vehicleInformation);
}