package com.campus.grid.controller;

import com.campus.common.core.util.R;
import com.campus.common.log.annotation.SysLog;
import com.campus.grid.api.entity.ReseauSchoolBus;
import com.campus.grid.service.PersonnelPostSettingService;
import com.campus.grid.service.ReseauSchoolBusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 网格划分-校车设备
 */

@Api(description = "网格划分-校车设备")
@RestController
@AllArgsConstructor
@RequestMapping("/schoolBus")
public class ReseauSchoolBusController {

    private ReseauSchoolBusService reseauSchoolBusService;
    private PersonnelPostSettingService personnelPostSettingService;

    @ApiOperation(value = "获取学校", notes="获取当前用户权限下的所有学校")
    @SysLog("获取当前用户权限下的所有学校")
    @GetMapping("/getSchool")
    public R getSchool() {
        return new R<>(personnelPostSettingService.getSchool());
    }

    @SysLog("判断是否存在重复校车编号")
    @GetMapping("/isBusId")
    public R isBusId(String carName){
        return new R<>(reseauSchoolBusService.isBusId(carName));
    }

    @ApiOperation(value = "新增校车", notes="新增校车检查信息")
    @SysLog("新增校车检查信息")
    @PostMapping("/addSchoolBus")
    public R addSchoolBus(ReseauSchoolBus reseauSchoolBus){
        if (reseauSchoolBusService.addSchoolBus(reseauSchoolBus)){
            return new R();
        }else {
            return new R(new RuntimeException("已经存在该检查子项,请勿重复添加"));
        }
    }

    @SysLog("编辑检查子项名称")
    @RequestMapping("/updateCheckName")
    public R updateCheckName(ReseauSchoolBus reseauSchoolBus){
        if (reseauSchoolBusService.updateCheckName(reseauSchoolBus)){
            return new R<>();
        }else {
            return new R(new RuntimeException("参数异常,请刷新页面后重新输入"));
        }
    }

    @SysLog("根据校车id删除校车")
    @RequestMapping("/deleteSchoolBus")
    public R deleteSchoolBus(String carId){
        return new R(reseauSchoolBusService.deleteSchoolBusByCarId(carId));
    }

    @SysLog("根据网格id删除子项检查")
    @RequestMapping("/deleteCarReseauId")
    public R deleteCarReseauId(String carReseauId){
        return new R(reseauSchoolBusService.deleteCarReseauId(carReseauId));
    }

    @SysLog("得到校车设备模块")
    @GetMapping("/getCarReseauId")
    public R getCarName(String schoolId, String topType, String carId){
        return new R(reseauSchoolBusService.getCarName(schoolId, topType, carId));
    }
}
