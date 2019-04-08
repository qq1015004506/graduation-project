package pers.quzhiyu.graduationproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.quzhiyu.graduationproject.domain.Staff;
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
    public int insertStaff(Staff staff) {
        return staffMapper.insertStaff(staff);
    }

    @Override
    public List<Staff> findAllCoder() {
        return staffMapper.findAllCoder();
    }

    @Override
    public List<Staff> findAllManager() {
        return staffMapper.findAllManager();
    }

    @Override
    public List<Staff> findAllTester() {
        return staffMapper.findAllTester();
    }
}
