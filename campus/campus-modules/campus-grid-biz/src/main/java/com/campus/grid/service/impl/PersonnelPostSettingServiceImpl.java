package com.campus.grid.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.common.core.util.R;
import com.campus.common.security.util.SecurityUtils;
import com.campus.grid.api.entity.PersonnelInfo;
import com.campus.grid.api.entity.PersonnelPostSetting;
import com.campus.grid.api.vo.PersonnelPostSettingVO;
import com.campus.grid.api.vo.PostUserVO;
import com.campus.grid.mapper.PersonnelPostSettingMapper;
import com.campus.grid.service.PersonnelPostSettingService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("PersonnelPostSettingService")
public class PersonnelPostSettingServiceImpl extends ServiceImpl<PersonnelPostSettingMapper, PersonnelPostSetting> implements PersonnelPostSettingService {

    @Override
    public List<PersonnelPostSettingVO> getSchool() {
        //获取当前用户信息
        String deptId = SecurityUtils.getUser().getDeptId();
        if (!"".equals(deptId) && deptId != null){
            List<PersonnelPostSettingVO> school = this.baseMapper.getSchool(deptId);
            return school;
        }else {
            return null;
        }
    }

    /**
     * 查询所有人员岗位信息
     * @param page
     * @return
     */
    @Override
    public Page<List<PersonnelPostSetting>> getAllPersonnelPost(Page page, String schoolId) {
        return page.setRecords(this.baseMapper.getPersonnelPostSetting(page, schoolId));
    }

    /**
     * 新增人员岗位信息
     * @param personnelPostSetting
     * @return
     */
    @Override
    public R addPersonnelPost(PersonnelPostSetting personnelPostSetting) {
        //新增人员岗位信息
        personnelPostSetting.setTime(new Date());
        return new R<>(this.baseMapper.addPersonnelPost(personnelPostSetting));
    }

    /**
     * 修改人员岗位信息
     * @param personnelPostSetting
     * @return
     */
    @Override
    public R updatePersonnelPost(PersonnelPostSetting personnelPostSetting) {
        //判断是否有该岗位
        String postId = this.baseMapper.selectPersonnelPostById(personnelPostSetting.getPostId());
        if (!"".equals(postId) && postId != null){
            personnelPostSetting.setTime(new Date());
            return new R<>(this.baseMapper.updatePersonnelPost(personnelPostSetting));
        }else {
            return new R<>(new RuntimeException("没有该岗位信息"));
        }

    }

    /**
     * 查询指定人员岗位信息
     * @param id 人员岗位id
     * @return
     */
    @Override
    public R getPersonnelPostById(String id) {
        if (!"".equals(id) && id != null){
            return new R<>(this.baseMapper.getPersonnelPostById(id));
        }
        return null;
    }

    /**
     * 根据学校id查询该所有岗位
     * @param schoolId  学校id
     * @return
     */
    @Override
    public List<PersonnelPostSettingVO> getPost(String schoolId) {
        if (!"".equals(schoolId) && schoolId != null){
            return this.baseMapper.selectPost(schoolId);
        }else {
            return null;
        }
    }

    /**
     * 根据id删除人员岗位信息
     * @param postId  人员岗位id
     * @return
     */
    @Override
    public boolean deletePostById(String postId) {
        if (!"".equals(postId) && postId != null){
            if (this.baseMapper.deletePostById(postId)){
                return this.baseMapper.deletePostSchool(postId);
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * 根据学校id查询出该学校的所有用户
     * @param schoolId  学校id
     * @return
     */
    @Override
    public List<PostUserVO> getUser(String schoolId) {
        return this.baseMapper.getUser(schoolId);
    }

    /**
     * 根据岗位id查询该岗位下的所有人员信息
     * @param id 岗位id
     * @return
     */
    @Override
    public List<PersonnelInfo> getPersonnelInfo(String id) {
        return this.baseMapper.getPersonnelInfo(id);
    }

    /**
     * 根据id删除人员信息
     * @param postId 岗位id
     * @param supervisorId 人员信息
     */
    @Override
    public boolean deleteSupervisorInfo(String postId, String supervisorId) {
        return this.baseMapper.deleteSupervisorInfo(postId, supervisorId);
    }

    /**
     * 新增部门人员
     * @param personnelInfo 人员信息实体类
     * @return
     */
    @Override
    public R savePersonnelInfo(PersonnelInfo personnelInfo) {
        //如果更改主管则更新人员岗位信息
        if ("1".equals(personnelInfo.getType())){
            PersonnelPostSetting personnelPostSetting = new PersonnelPostSetting();
            personnelPostSetting.setPostId(Integer.parseInt(personnelInfo.getSchoolDivisionId()));
            personnelPostSetting.setSupervisorId(personnelInfo.getSupervisorId());
            personnelPostSetting.setTime(new Date());
            this.baseMapper.updateSupervisorId(personnelPostSetting);
            //原本主管的状态改为0
            String schoolDivisionId = personnelInfo.getSchoolDivisionId();
            String supervisorId = personnelInfo.getSupervisorId();
            if (!"".equals(schoolDivisionId) && schoolDivisionId != null && !"".equals(supervisorId) && supervisorId != null){
                this.baseMapper.updateType(personnelInfo);
            }
        }
        return new R<>(this.baseMapper.addPostSchool(personnelInfo));
    }

    /**
     * 缓存人员信息
     * @param personnelInfo 人员信息实体类
     * @return
     */
    @Override
    public R cachePersonnelInfo(PersonnelInfo personnelInfo) {
        personnelInfo.setSchoolDivisionId(UUID.randomUUID().toString().replaceAll("-", ""));
        this.baseMapper.cachePersonnelInfo(personnelInfo);
        return new R<>(personnelInfo.getSchoolDivisionId());
    }

    /**
     * 得到缓存表信息
     * @param id 缓存表id
     * @return
     */
    @Override
    public R getCache(String id) {
        return new R<>(this.baseMapper.selectCache(id));
    }
}
