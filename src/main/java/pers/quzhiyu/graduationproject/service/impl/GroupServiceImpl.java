package pers.quzhiyu.graduationproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.quzhiyu.graduationproject.domain.Group;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.dto.GroupCount;
import pers.quzhiyu.graduationproject.dto.GroupInfo;
import pers.quzhiyu.graduationproject.dto.GroupStaff;
import pers.quzhiyu.graduationproject.dto.StaffTask;
import pers.quzhiyu.graduationproject.mapper.GroupMapper;
import pers.quzhiyu.graduationproject.mapper.StaffMapper;
import pers.quzhiyu.graduationproject.service.GroupService;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    StaffMapper staffMapper;

    @Override
    public List<GroupInfo> findAllGroup() {
        return groupMapper.findAllGroup();
    }

    @Override
    public GroupInfo findGroupById(Long id) {
        return groupMapper.findGroupById(id);
    }

    @Override
    @Transactional
    public int updateGroup(GroupInfo groupInfo) {
        groupMapper.cleanGroupInfoById(groupInfo.getId());
        staffMapper.changeStaffsGroup(groupInfo.getStaffs(),groupInfo.getId());
        return groupMapper.updateGroup(groupInfo);
    }

    @Override
    @Transactional
    public int insertGroup(GroupInfo group) {
        //顺序不能变,因为先插入组才能获得新组的id
        groupMapper.insertGroup(group);
        staffMapper.changeStaffsGroup(group.getStaffs(),group.getId());
        return 1;
    }

    @Override
    @Transactional
    public int deleteGroupById(Long id) {
        groupMapper.cleanGroupInfoById(id);
        return groupMapper.deleteGroupById(id);
    }


    @Override
    public List<GroupStaff> findAllGroupStaff() {
        return groupMapper.findAllGroupStaff();
    }

    @Override
    public List<GroupCount> findAllGroupCount() {
        return groupMapper.findAllGroupCount();
    }

    @Override
    public List<StaffTask> findAllStaffTask(Long id) {
        return groupMapper.findAllStaffTask(id);
    }


}
