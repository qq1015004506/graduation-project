package pers.quzhiyu.graduationproject.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.quzhiyu.graduationproject.dto.GroupCount;
import pers.quzhiyu.graduationproject.dto.GroupInfo;
import pers.quzhiyu.graduationproject.dto.GroupStaff;
import pers.quzhiyu.graduationproject.dto.StaffTask;
import pers.quzhiyu.graduationproject.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/group")
@CrossOrigin
public class GroupController {
    @Autowired
    GroupService groupService;

    @GetMapping("/count")
    @ApiOperation("查询分组服务")
    public List<GroupCount> findAllGroupCount() {
        return groupService.findAllGroupCount();
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation("通过组ID查询分组服务")
    public GroupInfo findGroupById(@PathVariable Long id) {
        return groupService.findGroupById(id);
    }

    @GetMapping()
    @ApiOperation("获取分组以及分组成员信息服务")
    public List<GroupInfo> findAllGroup() {
        return groupService.findAllGroup();
    }

    @GetMapping("/staff")
    @ApiOperation("获取所有成员以及成员所属小组的信息服务")
    public List<GroupStaff> findAllGroupStaff(){
        return groupService.findAllGroupStaff();
    }

    @PostMapping()
    @ApiOperation("创建分组服务")
    public int CreateGroup(@RequestBody GroupInfo groupInfo) {
        return groupService.insertGroup(groupInfo);
    }

    @PutMapping()
    @ApiOperation("更新分组服务")
    public int UpdateGroup(@RequestBody GroupInfo groupInfo) {
        return groupService.updateGroup(groupInfo);
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除分组服务")
    public int deleteGroup(@PathVariable Long id){
        return groupService.deleteGroupById(id);
    }

    @GetMapping("/{id:\\d+}/staffTask")
    @ApiOperation("通过分组ID查询组内所有成员的所有任务服务")
    public List<StaffTask> findAllStaffTask(@PathVariable Long id) {
        return groupService.findAllStaffTask(id);
    }
}
