package pers.quzhiyu.graduationproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.mapper.LoginMapper;
import pers.quzhiyu.graduationproject.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginMapper loginMapper;

    @Override
    public Staff login(Staff staff) {
        return loginMapper.login(staff.getUsername(),staff.getPassword());
    }
}
