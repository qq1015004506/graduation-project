package pers.quzhiyu.graduationproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.service.LoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping()
    public Staff login(@RequestBody Staff staff) {
        System.out.println(staff);
        return loginService.login(staff);
    }
}
