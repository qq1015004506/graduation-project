package pers.quzhiyu.graduationproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.quzhiyu.graduationproject.domain.Code;
import pers.quzhiyu.graduationproject.mapper.CodeMapper;
import pers.quzhiyu.graduationproject.service.CodeService;

import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    CodeMapper codeMapper;

    @Override
    public Code findCodeById(Long id) {
        return codeMapper.findCodeById(id);
    }

    @Override
    public List<Code> findAllCode() {
        return codeMapper.findAllCode();
    }

    @Override
    public int insertCode(Code code) {
        return codeMapper.insertCode(code);
    }

    @Override
    public int updateCode(Code code) {
        return codeMapper.updateCode(code);
    }

    @Override
    public int deleteCodeById(Long id) {
        return codeMapper.deleteCodeById(id);
    }

    @Override
    public List<Code> findAllCodeByTaskId(Long id) {
        return codeMapper.findAllCodeByTaskId(id);
    }
}
