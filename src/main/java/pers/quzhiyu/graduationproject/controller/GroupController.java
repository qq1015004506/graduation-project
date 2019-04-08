package pers.quzhiyu.graduationproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.quzhiyu.graduationproject.domain.Group;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.service.GroupService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group")
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
    @CrossOrigin
    public int AddMemberToGroup(@PathVariable Long id, @RequestBody List<Long>  groupMember) {
        System.out.println(groupMember);
        System.out.println(id);
        return 1;
//        return groupService.addMemberToGroup(id,groupMember);
    }

    @GetMapping("/test")
    public Map<String, List> hehe() {
        Map<String,List> res = new HashMap<>();
        List<Integer> arr = Arrays.asList(1,2,3,4,5);
        res.put("data",arr);
        return res;
    }

    @GetMapping()
    public List<Group> findAllGroup() {
        return groupService.findAllGroup();
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
