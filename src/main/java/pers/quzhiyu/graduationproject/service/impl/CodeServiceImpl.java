package pers.quzhiyu.graduationproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.quzhiyu.graduationproject.domain.Code;
import pers.quzhiyu.graduationproject.mapper.CodeMapper;
import pers.quzhiyu.graduationproject.service.CodeService;

import java.io.*;
import java.util.ArrayList;
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

    private String folder = "D:\\source\\graduation-project\\src\\main\\java\\pers\\quzhiyu\\graduationproject\\controller";

    public String readFileToString(File file) throws IOException {
        StringBuilder sb;
        try(InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
            BufferedReader bufferedReader = new BufferedReader(isr)) {
            sb = new StringBuilder();
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                sb.append(lineTxt+"\n");
            }
        }
        return sb.toString();
    }

    @Override
    public List<String> findAllCodeDetailsByTaskId(Long id) {
        List<Code> allCodeByTaskId = codeMapper.findAllCodeByTaskId(id);
        List<String> result = new ArrayList<>();
        for (Code code : allCodeByTaskId) {
            File f = new File(folder,code.getFilename());
            try {
                result.add(readFileToString(f));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
