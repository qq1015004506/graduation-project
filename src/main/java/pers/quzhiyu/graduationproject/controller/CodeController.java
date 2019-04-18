package pers.quzhiyu.graduationproject.controller;

import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("通过id提供代码信息查询服务")
    public Code findCodeById(@PathVariable Long id) {
        return codeService.findCodeById(id);
    }

    @GetMapping()
    @ApiOperation("代码查询服务")
    public List<Code> findAllCode() {
        return codeService.findAllCode();
    }

    @PostMapping()
    @ApiOperation("代码创建服务")
    public int CreateCode(@RequestBody Code code) {
        return codeService.insertCode(code);
    }

    @PutMapping()
    @ApiOperation("代码更新服务")
    public int UpdateCode(@RequestBody Code code) {
        return codeService.updateCode(code);
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("代码删除服务")
    public int deleteCode(@PathVariable Long id){
        return codeService.deleteCodeById(id);
    }


}
