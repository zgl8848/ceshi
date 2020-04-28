package com.campus.grid.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.common.core.util.R;
import com.campus.common.log.annotation.SysLog;
import com.campus.grid.api.entity.GroupMember;
import com.campus.grid.service.GroupMemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 
 *
 * @author campus
 * @date 2019-06-20 15:50:43
 */
@RestController
@AllArgsConstructor
@RequestMapping("/groupmember")
public class GroupMemberController {

  private final GroupMemberService groupMemberService;

  /**
   * 分页查询
   * @param page 分页对象
   * @param groupMember 
   * @return
   */
  @GetMapping("/page")
  public R getGroupMemberPage(Page page, GroupMember groupMember) {
    return  new R<>(groupMemberService.page(page,Wrappers.query(groupMember)));
  }


  /**
   * 通过id查询
   * @param groupId id
   * @return R
   */
  @GetMapping("/{groupId}")
  public R getById(@PathVariable("groupId") String groupId){
    return new R<>(groupMemberService.getById(groupId));
  }

  /**
   * 新增
   * @param groupMember 
   * @return R
   */
  @SysLog("新增")
  @PostMapping
  public R save(@RequestBody GroupMember groupMember){
    return new R<>(groupMemberService.save(groupMember));
  }

  /**
   * 修改
   * @param groupMember 
   * @return R
   */
  @SysLog("修改")
  @PutMapping
  public R updateById(@RequestBody GroupMember groupMember){
    return new R<>(groupMemberService.updateById(groupMember));
  }

  /**
   * 通过id删除
   * @param groupId id
   * @return R
   */
  @SysLog("删除")
  @DeleteMapping("/{groupId}")
  public R removeById(@PathVariable String groupId){
    return new R<>(groupMemberService.removeById(groupId));
  }

}
