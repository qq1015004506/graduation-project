package pers.quzhiyu.graduationproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pers.quzhiyu.graduationproject.domain.Group;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.dto.GroupInfo;
import pers.quzhiyu.graduationproject.dto.GroupStaff;
import pers.quzhiyu.graduationproject.service.GroupService;
import pers.quzhiyu.graduationproject.service.StaffService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group")
@CrossOrigin
public class GroupController {
    @Autowired
    GroupService groupService;
    @Autowired
    StaffService staffService;

    @GetMapping("/{id:\\d+}")
    public Group findGroupById(@PathVariable Long id) {
        return groupService.findGroupById(id);
    }

    @DeleteMapping("/{id:\\d+}/member/{ids}")
    public int deleteMemberFromGroup(@PathVariable Long id, @PathVariable List<Long>  ids) {

        return groupService.deleteMemberFromGroup(id,ids);
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
    @Transactional
    public int CreateGroup(@RequestBody GroupInfo groupInfo) {
        groupService.insertGroup(groupInfo);
        staffService.changeStaffsGroup(groupInfo.getStaffs(),groupInfo.getId());
        return 1;
    }

    @PutMapping()
    public int UpdateGroup(@RequestBody Group group) {
        return groupService.updateGroup(group);
    }

    @DeleteMapping("/{id:\\d+}")
    public int deleteGroup(@PathVariable Long id){
        return groupService.deleteGroupById(id);
    }
}
