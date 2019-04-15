package pers.quzhiyu.graduationproject.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.quzhiyu.graduationproject.domain.Code;
import pers.quzhiyu.graduationproject.dto.FileInfo;
import pers.quzhiyu.graduationproject.service.CodeService;

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
    private String folder = "D:\\source\\graduation-project\\src\\main\\java\\pers\\quzhiyu\\graduationproject\\controller";

    @Autowired
    CodeService codeService;

    public String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    @GetMapping("/task/{id:\\d+}")
    public List<Code> findAllCodeByTaskId(@PathVariable Long id) {
        return codeService.findAllCodeByTaskId(id);
    }


    @GetMapping("/task/{id:\\d+}/details")
    public List<String> findAllCodeDetailsByTaskId(@PathVariable Long id) {
        return codeService.findAllCodeDetailsByTaskId(id);
    }

    @PostMapping
    public FileInfo upload(MultipartFile file, Code code) throws IOException {
        String fileName = new Date().getTime()+"";
        code.setUploadTime(new Timestamp(new Date().getTime()));
        code.setFilename(fileName);

        File localFile = new File(folder,fileName);
        file.transferTo(localFile);

        codeService.insertCode(code);

        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        try (
            InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
            OutputStream outputStream = response.getOutputStream();)
        {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
