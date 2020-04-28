package com.campus.grid.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.common.core.util.R;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.dto.PageBean;
import com.campus.grid.api.entity.VehicleInformation;
import com.campus.grid.service.VehicleInformationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/vehicleInformation")
@RestController
public class VehicleInformationController {

	private VehicleInformationService vehicleInformationService;

	@RequestMapping("/page")
	public R getVehicleInformationPage(Page page, VehicleInformation vehicleInformation){
		String vid = UUID.randomUUID().toString().replace("-", "");
		PageBean pageBean = vehicleInformationService.queryPage(page,vehicleInformation);
		return new R(pageBean);
	}

	@DeleteMapping("/{vid}")
	public void deleteById(@PathVariable("vid") String vId){
		vehicleInformationService.deleteById(vId);
		SecurityUtils.getAuthentication();
	}

	@PutMapping
	public void editVehicleInformation(@RequestBody VehicleInformation vehicleInformation){
		vehicleInformationService.upedate(vehicleInformation);
	}

}
