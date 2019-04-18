package pers.quzhiyu.graduationproject.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.service.StaffService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/staff")
@CrossOrigin
public class StaffController {
    @Autowired
    StaffService staffService;

    @PostMapping
    @ApiOperation("创建员工服务")
    public int createStaff(@RequestBody Staff staff) {
        return staffService.insertStaff(staff);
    }

    @GetMapping()
    @ApiOperation("获取员工信息服务")
    public Map<String,Object> findAllStaff(@RequestParam(value = "current",defaultValue = "1")int current,
                                    @RequestParam(value = "size",defaultValue = "10")int size){
        PageHelper.startPage(current,size);
        List<Staff> allStaff = staffService.findAllStaff();
        PageInfo<Staff> pageInfo = new PageInfo<>(allStaff);
        Map<String,Object> data = new HashMap<>();
        data.put("total",pageInfo.getTotal());//总条数
        data.put("current",current);//当前页
        data.put("data",pageInfo.getList());//数据
        return data;
    }


    @GetMapping("/query")
    @ApiOperation("通过员工工作以及姓名查询员工服务")
    public Map<String,Object> queryStaff(Long job, String name,
                                    @RequestParam(value = "current",defaultValue = "1")int current,
                                    @RequestParam(value = "size",defaultValue = "10")int size) {
        PageHelper.startPage(current,size);
        List<Staff> staff = staffService.queryStaff(name, job);
        PageInfo<Staff> pageInfo = new PageInfo<>(staff);
        Map<String,Object> data = new HashMap<>();
        data.put("total",pageInfo.getTotal());//总条数
        data.put("current",current);//当前页
        data.put("data",pageInfo.getList());//数据
        return data;
    }

    @GetMapping("/group/{group:\\d+}")
    @ApiOperation("通过分组ID查询所有员工服务")
    public List<Staff> findAllStaffByGroupId(@PathVariable Long group) {
        return staffService.findAllStaffByGroupId(group);
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation("通过员工ID查询员工服务")
    public Staff findStaffById(@PathVariable Long id) {
        return staffService.findStaffById(id);
    }

    @PutMapping()
    @ApiOperation("更新员工信息服务")
    public int updateStaff(@RequestBody Staff staff) {
        return staffService.updateStaff(staff);
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除员工服务")
    public int deleteStaff(@PathVariable Long id) {
        return staffService.deleteStaffById(id);
    }
}
