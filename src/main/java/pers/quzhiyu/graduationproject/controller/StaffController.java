package pers.quzhiyu.graduationproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.service.StaffService;

import java.util.List;

@RestController
@RequestMapping("/sdf")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/{id:\\d+}")
    public Staff findStaffById(@PathVariable Long id) {
        return staffService.findStaffById(id);
    }

    @GetMapping()
    public List<Staff> findAllStaff(){
        return staffService.findAllStaff();
    }

    @PostMapping()
    public int createStaff(@RequestBody Staff staff) {
        return staffService.insertStaff(staff);
    }

    @PutMapping()
    public int updateStaff(@RequestBody Staff staff) {
        return staffService.updateStaff(staff);
    }

    @DeleteMapping("/{id:\\d+}")
    public int deleteStaffById(@PathVariable Long id) {
        return staffService.deleteStaffById(id);
    }
}
