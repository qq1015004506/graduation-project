package pers.quzhiyu.graduationproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.quzhiyu.graduationproject.domain.Code;
import pers.quzhiyu.graduationproject.domain.Task;
import pers.quzhiyu.graduationproject.mapper.CodeMapper;
import pers.quzhiyu.graduationproject.mapper.TaskMapper;
import pers.quzhiyu.graduationproject.service.CodeService;

import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    CodeMapper codeMapper;

    @Autowired
    TaskMapper taskMapper;

    @Override
    public Code findCodeById(Long id) {
        return codeMapper.findCodeById(id);
    }

    @Override
    public List<Code> findAllCode() {
        return codeMapper.findAllCode();
    }

    @Override
    @Transactional
    public int insertCode(Code code) {
        Task t = taskMapper.findTaskById(code.getTaskId());
        t.setStage(1L);
        t.setId(code.getTaskId());
        codeMapper.insertCode(code);
        t.setCodeId(code.getId());
        taskMapper.updateTask(t);
        return 1;
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
