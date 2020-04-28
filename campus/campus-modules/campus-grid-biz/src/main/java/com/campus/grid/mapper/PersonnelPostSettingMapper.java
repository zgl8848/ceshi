package com.campus.grid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.grid.api.entity.PersonnelInfo;
import com.campus.grid.api.entity.PersonnelPostSetting;
import com.campus.grid.api.vo.PersonnelPostSettingVO;
import com.campus.grid.api.vo.PostUserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonnelPostSettingMapper extends BaseMapper<PersonnelPostSetting>{
    List<PersonnelPostSettingVO> getSchool(String deptId);

    List<PersonnelPostSetting> getPersonnelPostSetting(@Param("page") Page page, @Param("schoolId") String schoolId);

    String selectUserIdByPersonnelName(PersonnelPostSetting personnelPostSetting);

    boolean addPersonnelPost(PersonnelPostSetting personnelPostSetting);

    boolean addPostSchool(PersonnelInfo personnelInfo);

    String selectPersonnelPostById(int postId);

    boolean updatePersonnelPost(PersonnelPostSetting personnelPostSetting);

    void updatePostSchool(@Param("postId") int postId, @Param("userId") String userId, @Param("remarks") String remarks);

    PersonnelPostSetting getPersonnelPostById(String id);

    List<PersonnelPostSettingVO> selectPost(String schoolId);

    boolean deletePostById(String postId);

    boolean deletePostSchool(String postId);

    List<PostUserVO> getUser(String schoolId);

    List<PersonnelInfo> getPersonnelInfo(String id);

    boolean deleteSupervisorInfo(@Param("postId") String postId, @Param("supervisorId") String supervisorId);

    void updateSupervisorId(PersonnelPostSetting personnelPostSetting);

    void cachePersonnelInfo(PersonnelInfo personnelInfo);

    PersonnelInfo selectCache(String id);

    void updateType(PersonnelInfo personnelInfo);
}
