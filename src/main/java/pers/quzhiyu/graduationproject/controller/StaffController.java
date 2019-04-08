package pers.quzhiyu.graduationproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.service.StaffService;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    StaffService staffService;

    @PostMapping
    public int createStaff(@RequestBody Staff staff) {
        return staffService.insertStaff(staff);
    }

    @GetMapping()
    public List<Staff> findAllStaff(){
        return staffService.findAllStaff();
    }

    @GetMapping("/coder")
    public List<Staff> findAllCoder() {
        return staffService.findAllCoder();
    }
    @GetMapping("/manager")
    public List<Staff> findAllManager() {
        return staffService.findAllManager();
    }
    @GetMapping("/tester")
    public List<Staff> findAllTester() {
        return staffService.findAllTester();
    }

    @GetMapping("/{id:\\d+}")
    public Staff findStaffById(@PathVariable Long id) {
        return staffService.findStaffById(id);
    }

    @PutMapping()
    public int updateStaff(@RequestBody Staff staff) {
        return staffService.updateStaff(staff);
    }

    @DeleteMapping("/{id:\\d+}")
    public int deleteStaff(@PathVariable Long id) {
        return staffService.deleteStaffById(id);
    }
}
