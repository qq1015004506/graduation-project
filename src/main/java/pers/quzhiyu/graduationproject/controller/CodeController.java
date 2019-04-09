package pers.quzhiyu.graduationproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.quzhiyu.graduationproject.domain.Code;
import pers.quzhiyu.graduationproject.service.CodeService;

import java.util.List;

@RestController
@RequestMapping("/code")
public class CodeController {
    @Autowired
    CodeService codeService;

    @GetMapping("/{id:\\d+}")
    public Code findCodeById(@PathVariable Long id) {
        return codeService.findCodeById(id);
    }

    @GetMapping()
    public List<Code> findAllCode() {
        return codeService.findAllCode();
    }

    @PostMapping()
    public int CreateCode(@RequestBody Code code) {
        return codeService.insertCode(code);
    }

    @PutMapping()
    public int UpdateCode(@RequestBody Code code) {
        return codeService.updateCode(code);
    }

    @DeleteMapping("/{id:\\d+}")
    public int deleteCode(@PathVariable Long id){
        return codeService.deleteCodeById(id);
    }


}
