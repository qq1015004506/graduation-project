package pers.quzhiyu.graduationproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.dto.StaffCount;
import pers.quzhiyu.graduationproject.mapper.StaffMapper;
import pers.quzhiyu.graduationproject.service.StaffService;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffMapper staffMapper;

    @Override
    public List<Staff> findAllStaff() {
        return staffMapper.findAll();
    }

    @Override
    public Staff findStaffById(Long id) {
        return staffMapper.findStaffById(id);
    }

    @Override
    public int updateStaff(Staff staff) {
        return staffMapper.updateStaff(staff);
    }

    @Override
    public int deleteStaffById(Long id) {
        return staffMapper.deleteStaffById(id);
    }

    @Override
    @Transactional
    public int insertStaff(Staff staff) {
        int n = staffMapper.findStaffByUsername(staff.getUsername());
        if(n > 0)
            return 0;
        return staffMapper.insertStaff(staff);
    }

    @Override
    public List<Staff> queryStaff(String name, Long job) {
        if(name != null && job != null)
            return staffMapper.queryStaff(name,job);
        if(job != null)
            return staffMapper.queryStaffByJob(job);
        if(name != null)
            return staffMapper.queryStaffByName(name);
        return staffMapper.findAll();
    }

    @Override
    public int changeStaffsGroup(List<StaffCount> staffs, Long newId) {
        return staffMapper.changeStaffsGroup(staffs,newId);
    }

    @Override
    public List<Staff> findAllStaffByGroupId(Long group) {
        return staffMapper.findAllStaffByGroupId(group);
    }

}
