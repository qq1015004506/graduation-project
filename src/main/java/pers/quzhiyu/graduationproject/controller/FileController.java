package pers.quzhiyu.graduationproject.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.quzhiyu.graduationproject.domain.Code;
import pers.quzhiyu.graduationproject.domain.Task;
import pers.quzhiyu.graduationproject.service.CodeService;
import pers.quzhiyu.graduationproject.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {

    @Autowired
    CodeService codeService;
    @Autowired
    TaskService taskService;

    @GetMapping("/task/{id:\\d+}")
    public List<Code> findAllCodeByTaskId(@PathVariable Long id) {
        return codeService.findAllCodeByTaskId(id);
    }




    @PostMapping
    public int upload(MultipartFile file, Code code) throws IOException {
        String fileName = new Date().getTime()+"";
        code.setUploadTime(new Timestamp(new Date().getTime()));
        code.setFilename(fileName);
        code.setCodeDetail(IOUtils.toString(file.getInputStream()));
        return codeService.insertCode(code);
    }

    @GetMapping("/{id}")
    @Transactional
    public void download(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Task task= taskService.findTaskById(id);
        String codeDetail = codeService.findCodeById(task.getCodeId()).getCodeDetail();
        System.out.println(codeDetail);
        InputStream inputStream = new ByteArrayInputStream(codeDetail.getBytes());
        OutputStream outputStream = response.getOutputStream();
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition", "attachment;filename=test.java");
        IOUtils.copy(inputStream,outputStream);
        outputStream.flush();

        inputStream.close();
        outputStream.close();
    }
}
