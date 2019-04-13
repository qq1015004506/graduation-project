package pers.quzhiyu.graduationproject.service;

import org.springframework.stereotype.Service;
import pers.quzhiyu.graduationproject.domain.Group;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.dto.GroupCount;
import pers.quzhiyu.graduationproject.dto.GroupInfo;
import pers.quzhiyu.graduationproject.dto.GroupStaff;

import java.util.List;

@Service
public interface GroupService {
    List<GroupInfo> findAllGroup();

    GroupInfo findGroupById(Long id);

    int updateGroup(final GroupInfo groupInfo);

    int insertGroup(GroupInfo group);

    int deleteGroupById(Long id);


    List<GroupStaff> findAllGroupStaff();

    List<GroupCount> findAllGroupCount();
}
