package pers.quzhiyu.graduationproject.service;

import pers.quzhiyu.graduationproject.domain.Staff;

import java.util.List;

public interface StaffService {
    List<Staff> findAllStaff();

    Staff findStaffById(Long id);

    int updateStaff(Staff staff);

    int deleteStaffById(Long id);

    int insertStaff(Staff staff);


    List<Staff> queryStaff(String name, Long job);

    int changeStaffsGroup(List<Staff> staffs, Long newId);
}
