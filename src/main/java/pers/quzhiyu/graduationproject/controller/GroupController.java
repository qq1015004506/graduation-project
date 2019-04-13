package pers.quzhiyu.graduationproject.controller;

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
    public List<GroupCount> findAllGroupCount() {
        return groupService.findAllGroupCount();
    }

    @GetMapping("/{id:\\d+}")
    public GroupInfo findGroupById(@PathVariable Long id) {
        return groupService.findGroupById(id);
    }

    @GetMapping()
    public List<GroupInfo> findAllGroup() {
        return groupService.findAllGroup();
    }

    @GetMapping("/staff")
    public List<GroupStaff> findAllGroupStaff(){
        return groupService.findAllGroupStaff();
    }

    @PostMapping()
    public int CreateGroup(@RequestBody GroupInfo groupInfo) {
        return groupService.insertGroup(groupInfo);
    }

    @PutMapping()
    public int UpdateGroup(@RequestBody GroupInfo groupInfo) {
        return groupService.updateGroup(groupInfo);
    }

    @DeleteMapping("/{id:\\d+}")
    public int deleteGroup(@PathVariable Long id){
        return groupService.deleteGroupById(id);
    }

    @GetMapping("/{id:\\d+}/staffTask")
    public List<StaffTask> findAllStaffTask(@PathVariable Long id) {
        return groupService.findAllStaffTask(id);
    }
}
