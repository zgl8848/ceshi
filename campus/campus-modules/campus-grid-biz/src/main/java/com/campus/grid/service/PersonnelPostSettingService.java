package com.campus.grid.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.campus.common.core.util.R;
import com.campus.grid.api.entity.PersonnelPostSetting;
import com.campus.grid.api.entity.PersonnelInfo;
import com.campus.grid.api.vo.PersonnelPostSettingVO;
import com.campus.grid.api.vo.PostUserVO;

import java.util.List;

//人员岗位
public interface PersonnelPostSettingService extends IService<PersonnelPostSetting>{

    List<PersonnelPostSettingVO> getSchool();

    Page<List<PersonnelPostSetting>> getAllPersonnelPost(Page page, String schoolId);

    R addPersonnelPost(PersonnelPostSetting personnelPostSetting);

    R updatePersonnelPost(PersonnelPostSetting personnelPostSetting);

    R getPersonnelPostById(String id);

    List<PersonnelPostSettingVO> getPost(String schoolId);

    boolean deletePostById(String postId);

    List<PostUserVO> getUser(String schoolId);

    List<PersonnelInfo> getPersonnelInfo(String id);

    boolean deleteSupervisorInfo(String postId, String supervisorId);

    R savePersonnelInfo(PersonnelInfo personnelInfo);

    R cachePersonnelInfo(PersonnelInfo personnelInfo);

    R getCache(String id);
}
