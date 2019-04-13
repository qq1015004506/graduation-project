package pers.quzhiyu.graduationproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.quzhiyu.graduationproject.domain.Task;
import pers.quzhiyu.graduationproject.dto.TaskInfo;
import pers.quzhiyu.graduationproject.service.TaskService;

import java.util.ArrayList;
import java.util.List;
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

    @GetMapping()
    public List<Task> findAllTask() {
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
