package pers.quzhiyu.graduationproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.quzhiyu.graduationproject.domain.Group;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.dto.GroupStaff;
import pers.quzhiyu.graduationproject.service.GroupService;

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

    @GetMapping("/{id:\\d+}")
    public Group findGroupById(@PathVariable Long id) {
        return groupService.findGroupById(id);
    }

    @GetMapping("/{id:\\d+}/member")
    public List<Staff> findMemberByGroupId(@PathVariable Long id) {
        return groupService.findMemberByGroupId(id);
    }

    @PostMapping("/{id:\\d+}/member")
    public int addMemberToGroup(@PathVariable Long id, @RequestBody List<Long>  groupMember) {
        return groupService.addMemberToGroup(id,groupMember);
    }

    @DeleteMapping("/{id:\\d+}/member/{ids}")
    public int deleteMemberFromGroup(@PathVariable Long id, @PathVariable List<Long>  ids) {

        return groupService.deleteMemberFromGroup(id,ids);
    }


    @GetMapping()
    public List<Group> findAllGroup() {
        return groupService.findAllGroup();
    }

    @GetMapping("/staff")
    public List<GroupStaff> findAllGroupStaff(){
        return groupService.findAllGroupStaff();
    }

    @PostMapping()
    public int CreateGroup(@RequestBody Group group) {
        return groupService.insertGroup(group);
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
