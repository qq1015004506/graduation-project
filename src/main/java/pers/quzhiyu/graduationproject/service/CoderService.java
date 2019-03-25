package pers.quzhiyu.graduationproject.service;

import pers.quzhiyu.graduationproject.domain.Coder;

import java.util.List;

public interface CoderService {
    List<Coder> findAllCoder();

    Coder findCoderById(Integer id);
}
