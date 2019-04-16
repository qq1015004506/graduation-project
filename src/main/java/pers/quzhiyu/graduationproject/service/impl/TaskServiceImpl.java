package pers.quzhiyu.graduationproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public List<TaskInfo> findAllTask() {
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

    @Override
    public List<TaskInfo> findAllTask(String name, String staffName, Long group) {
        if(name == null )
            name = "";
        if(staffName == null)
            staffName = "";
        if(group != null)
            return taskMapper.queryTask(name,staffName,group);
        return taskMapper.queryTaskNoGroup(name,staffName);
    }

    @Override
    public List<Task> findTaskByStaffId(Long id) {
        return taskMapper.findTaskByStaffId(id);
    }

    @Override
    public int changeTaskCodeInfo(Long taskId, Long codeId) {
        return taskMapper.changeTaskCodeInfo(taskId,codeId);
    }

    @Override
    public List<Task> findTaskByGroupForTester(Long id) {
        return taskMapper.findTaskByGroupForTester(id);
    }

    @Override
    @Transactional
    public int createTestTask(Task task) {
        Task t = new Task();
        t.setId(task.getCodeId());
        t.setStage(3L);
        taskMapper.updateTask(t);
        return taskMapper.insertTask(task);
    }

    @Override
    @Transactional
    public int deleteTestTask(Long testId, Long taskId) {
        Task t = new Task();
        t.setId(taskId);
        Task task = taskMapper.findTaskById(taskId);
        if(task.getStage() == 3)
            t.setStage(1L);
        taskMapper.updateTask(t);
        return taskMapper.deleteTaskById(testId);
    }

    @Override
    @Transactional
    public int testResult(Task testTask) {
        Task t = new Task();
        t.setId(testTask.getCodeId());
        t.setStage(testTask.getStage());
        testTask.setStage(5L);
        taskMapper.updateTask(t);
        return taskMapper.updateTask(testTask);
    }
}
