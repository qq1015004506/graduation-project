package pers.quzhiyu.graduationproject.controller;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pers.quzhiyu.graduationproject.domain.Coder;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.dto.CoderQueryCondition;
import pers.quzhiyu.graduationproject.service.CoderService;
import pers.quzhiyu.graduationproject.service.StaffService;

import java.util.List;


@RestController
@RequestMapping("/user")
public class TestController {

    @Autowired
    CoderService coderService;

    @Autowired
    StaffService staffService;

    @GetMapping("/coder")
    public List<Coder> getCoder(CoderQueryCondition condition){
        PageHelper.startPage(condition.getPage(),condition.getSize(),condition.getSort());
        return coderService.findAllCoder();
    }

    @GetMapping("/{id:\\d+}")
    public Staff findStaffById(@PathVariable Long id) {
        return staffService.findStaffById(id);
    }

    @GetMapping("/coder/{id:\\d+}")
    public Coder getInfo(@PathVariable Integer id) {
        return coderService.findCoderById(id);
    }

    @PostMapping()
    public int createCoder(@RequestBody Staff staff){
        return staffService.insertStaff(staff);
    }

    @PutMapping("/coder/{id:\\d+}")
    public Coder updateCoder(@RequestBody Coder coder, BindingResult errors){

        if(errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                String message = fieldError.getField() + " " + fieldError.getDefaultMessage();
                System.out.println(message);
            });
        }

        System.out.println(coder);

        return coder;
    }

    @DeleteMapping("/coder/{id:\\d+}")
    public void deleteCoder(@PathVariable Integer id){


        System.out.println(id);
    }
}
