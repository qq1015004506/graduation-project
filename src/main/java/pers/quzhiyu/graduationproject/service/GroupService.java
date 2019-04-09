package pers.quzhiyu.graduationproject.service;

import org.springframework.stereotype.Service;
import pers.quzhiyu.graduationproject.domain.Group;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.dto.GroupInfo;

import java.util.List;

@Service
public interface GroupService {
    List<Group> findAllGroup();

    Group findGroupById(Long id);

    int updateGroup(final Group group);

    int insertGroup(Group group);

    int deleteGroupById(Long id);

    List<Staff> findMemberByGroupId(Long id);

    int addMemberToGroup(Long id, List<Long> groupMember);

    int deleteMemberFromGroup(Long id, List<Long> ids);
}
