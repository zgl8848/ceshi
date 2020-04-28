package com.campus.grid.controller;

import com.campus.common.core.util.R;
import com.campus.common.log.annotation.SysLog;
import com.campus.grid.service.PersonnelPostSettingService;
import com.campus.grid.service.ReseauPatrolService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schoolBusUser")
@AllArgsConstructor
public class ReseauPatrolController {

    private PersonnelPostSettingService personnelPostSettingService;
    private ReseauPatrolService reseauPatrolService;

    @SysLog("/获取当前用户权限下的所有学校")
    @GetMapping("/getSchool")
    public R getSchool(){
        return new R<>(personnelPostSettingService.getSchool());
    }
}
