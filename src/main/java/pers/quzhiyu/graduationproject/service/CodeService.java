package pers.quzhiyu.graduationproject.service;

import pers.quzhiyu.graduationproject.domain.Code;

import java.util.List;

public interface CodeService {
    Code findCodeById(Long id);

    List<Code> findAllCode();

    int insertCode(Code code);

    int updateCode(Code code);

    int deleteCodeById(Long id);

    List<Code> findAllCodeByTaskId(Long id);
}
