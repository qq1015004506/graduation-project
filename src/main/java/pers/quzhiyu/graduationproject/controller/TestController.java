package pers.quzhiyu.graduationproject.controller;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pers.quzhiyu.graduationproject.domain.Coder;
import pers.quzhiyu.graduationproject.dto.CoderQueryCondition;
import pers.quzhiyu.graduationproject.service.CoderService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/user")
public class TestController {

    @Autowired
    CoderService coderService;

    @GetMapping("/coder")
    public List<Coder> getCoder(CoderQueryCondition condition){
        PageHelper.startPage(condition.getPage(),condition.getSize(),condition.getSort());
        return coderService.findAllCoder();
    }

    @GetMapping("/coder/{id:\\d+}")
    public Coder getInfo(@PathVariable Integer id) {
        return coderService.findCoderById(id);
    }

    @PostMapping("/coder")
    public Coder createCoder(@Valid @RequestBody Coder coder, BindingResult errors){

        if(errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        System.out.println(coder);
        coder.setId(4);
        return coder;
    }


}
