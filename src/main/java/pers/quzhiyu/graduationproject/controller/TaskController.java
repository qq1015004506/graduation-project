package pers.quzhiyu.graduationproject.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.domain.Task;
import pers.quzhiyu.graduationproject.dto.TaskInfo;
import pers.quzhiyu.graduationproject.service.TaskService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
@CrossOrigin
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/{id:\\d+}")
    public Task findTaskById(@PathVariable Long id) {
        return taskService.findTaskById(id);
    }

    @GetMapping("/query")
    public Map<String,Object> queryTask(@RequestParam(value = "name",defaultValue = "")String name,
                                        @RequestParam(value = "staffName",defaultValue = "")String staffName,Long group,
                                        @RequestParam(value = "current",defaultValue = "1")int current,
                                        @RequestParam(value = "size",defaultValue = "10")int size) {
        PageHelper.startPage(current,size);
        List<TaskInfo> staff = taskService.findAllTask(name, staffName,group);
        PageInfo<TaskInfo> pageInfo = new PageInfo<>(staff);
        Map<String,Object> data = new HashMap<>();
        data.put("total",pageInfo.getTotal());//总条数
        data.put("current",current);//当前页
        data.put("data",pageInfo.getList());//数据
        return data;
    }

    @GetMapping()
    public List<TaskInfo> findAllTask() {
        return taskService.findAllTask();
    }

    @PostMapping()
    public int CreateTask(@RequestBody Task task) {
        return taskService.insertTask(task);
    }

    @PutMapping()
    public int UpdateTask(@RequestBody Task task) {
        return taskService.updateTask(task);
    }

    @DeleteMapping("/{id:\\d+}")
    public int deleteTask(@PathVariable Long id){
        return taskService.deleteTaskById(id);
    }

    @PostMapping("/distribute")
    public int distributeTask(@RequestBody TaskInfo taskInfo) {
        return taskService.distributeTask(taskInfo);
    }

    @PutMapping("/distribute")
    public int updateTaskInfo(@RequestBody TaskInfo taskInfo) {
        return taskService.updateTaskInfo(taskInfo);
    }

}
