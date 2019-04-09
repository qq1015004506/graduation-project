package pers.quzhiyu.graduationproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.quzhiyu.graduationproject.domain.Group;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.dto.GroupInfo;
import pers.quzhiyu.graduationproject.mapper.GroupMapper;
import pers.quzhiyu.graduationproject.service.GroupService;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupMapper groupMapper;

    @Override
    public List<Group> findAllGroup() {
        return groupMapper.findAllGroup();
    }

    @Override
    public Group findGroupById(Long id) {
        return groupMapper.findGroupById(id);
    }

    @Override
    public int updateGroup(Group group) {
        return groupMapper.updateGroup(group);
    }

    @Override
    public int insertGroup(Group group) {
        return groupMapper.insertGroup(group);
    }

    @Override
    public int deleteGroupById(Long id) {
        return groupMapper.deleteGroupById(id);
    }

    @Override
    public List<Staff> findMemberByGroupId(Long id) {
        return groupMapper.findMemberByGroupId(id);
    }

    @Override
    public int addMemberToGroup(Long id, List<Long> groupMember) {
        return groupMapper.addMemberToGroup(id,groupMember);
    }

    @Override
    public int deleteMemberFromGroup(Long id, List<Long> ids) {
        return groupMapper.deleteMemberFromGroup(id,ids);
    }


}
