package com.campus.grid.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.common.core.util.R;
import com.campus.common.log.annotation.SysLog;
import com.campus.grid.api.entity.PersonnelPostSetting;
import com.campus.grid.api.entity.PersonnelInfo;
import com.campus.grid.service.PersonnelPostSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personnelpostsetting")
public class PersonnelPostSettingController {

    @Autowired
    private PersonnelPostSettingService personnelPostSettingService;

    @SysLog("查询当前用户权限下的学校")
    @GetMapping("/getSchool")
    public R getSchool(){
        return new R<>(personnelPostSettingService.getSchool());
    }

    @SysLog("分页查询当前学校所有人员岗位信息")
    @GetMapping("/page")
    public R getAllPersonnelPost(Page page, String schoolId){
        return new R<>(personnelPostSettingService.getAllPersonnelPost(page, schoolId));
    }

    @SysLog("查询指定人员岗位信息")
    @GetMapping("/{id}")
    public R getPersonnelPostById(@PathVariable String id){
        return new R<>(personnelPostSettingService.getPersonnelPostById(id));
    }

    @SysLog("根据岗位id查询该岗位下的所有人员信息")
    @GetMapping("/info/{id}")
    public R getPersonnelInfo(@PathVariable String id){
        return new R<>(personnelPostSettingService.getPersonnelInfo(id));
    }

    @SysLog("根据学校id查询该学校所有岗位")
    @GetMapping("/post/{schoolId}")
    public R getPost(@PathVariable String schoolId){
        return new R<>(personnelPostSettingService.getPost(schoolId));
    }

    @SysLog("根据学校id查询出该学校的所有用户")
    @GetMapping("/user/{schoolId}")
    public R getUserBySchoolId(@PathVariable String schoolId){
        return new R<>(personnelPostSettingService.getUser(schoolId));
    }

    @SysLog("查询缓存信息")
    @GetMapping("/getCache")
    public R getCache(String id){
        return new R<>(personnelPostSettingService.getCache(id));
    }

    @SysLog("新增人员岗位信息")
    @PostMapping
    public R save(PersonnelPostSetting personnelPostSetting){
        return new R<>(personnelPostSettingService.addPersonnelPost(personnelPostSetting));
    }

    @SysLog("新增部门人员")
    @PostMapping("/info")
    public R savePersonnelInfo(PersonnelInfo personnelInfo){
        return new R<>(personnelPostSettingService.savePersonnelInfo(personnelInfo));
    }

    @SysLog("缓存人员信息")
    @PostMapping("/addCache")
    public R cachePersonnelInfo(PersonnelInfo personnelInfo){
        return new R<>(personnelPostSettingService.cachePersonnelInfo(personnelInfo));
    }

    @SysLog("修改人员岗位信息")
    @RequestMapping("/updatePost")
    public R update(PersonnelPostSetting personnelPostSetting){
        return new R<>(personnelPostSettingService.updatePersonnelPost(personnelPostSetting));
    }

    @SysLog("根据id删除人员信息")
    @RequestMapping("/deletePersonnel")
    public R deleteSupervisorInfo(String id, String supervisorId){
        return new R<>(personnelPostSettingService.deleteSupervisorInfo(id, supervisorId));
    }

    @SysLog("根据id删除人员岗位信息")
    @RequestMapping("/deletePost")
    public R deletePost(String postId){
        return new R<>(personnelPostSettingService.deletePostById(postId));
    }
}
