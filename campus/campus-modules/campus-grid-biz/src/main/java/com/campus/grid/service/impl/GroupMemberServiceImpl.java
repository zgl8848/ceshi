package com.campus.grid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campus.grid.api.entity.GroupMember;
import com.campus.grid.mapper.GroupMemberMapper;
import com.campus.grid.service.GroupMemberService;
import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author campus
 * @date 2019-06-20 15:50:43
 */
@Service("groupMemberService")
public class GroupMemberServiceImpl extends ServiceImpl<GroupMemberMapper, GroupMember> implements GroupMemberService {

}
