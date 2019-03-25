package pers.quzhiyu.graduationproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.quzhiyu.graduationproject.domain.Coder;
import pers.quzhiyu.graduationproject.mapper.CoderMapper;
import pers.quzhiyu.graduationproject.service.CoderService;

import java.util.List;
@Service
public class CoderServiceImpl implements CoderService {

    @Autowired
    CoderMapper coderMapper;

    @Override
    public List<Coder> findAllCoder() {
        return coderMapper.findAll();
    }

    @Override
    public Coder findCoderById(Integer id) {
        return coderMapper.findCoderById(id);
    }
}
