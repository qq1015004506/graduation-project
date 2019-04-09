package pers.quzhiyu.graduationproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.quzhiyu.graduationproject.domain.Task;
import pers.quzhiyu.graduationproject.dto.TaskInfo;
import pers.quzhiyu.graduationproject.mapper.TaskMapper;
import pers.quzhiyu.graduationproject.service.TaskService;

import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskMapper taskMapper;

    @Override
    public List<Task> findAllTask() {
        return taskMapper.findAllTask();
    }

    @Override
    public Task findTaskById(Long id) {
        return taskMapper.findTaskById(id);
    }

    @Override
    public int updateTask(Task task) {
        return taskMapper.updateTask(task);
    }

    @Override
    public int insertTask(Task task) {
        return taskMapper.insertTask(task);
    }

    @Override
    public int deleteTaskById(Long id) {
        return taskMapper.deleteTaskById(id);
    }

    @Override
    public int distributeTask(TaskInfo taskInfo) {
        return taskMapper.distributeTask(taskInfo);
    }

    @Override
    public int updateTaskInfo(TaskInfo taskInfo) {
        return taskMapper.updateTaskInfo(taskInfo);
    }
}
