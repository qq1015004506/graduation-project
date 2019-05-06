package pers.quzhiyu.graduationproject.service;

import org.springframework.stereotype.Service;
import pers.quzhiyu.graduationproject.domain.Staff;

public interface LoginService {
    public Staff login(Staff staff);
}
