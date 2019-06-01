package pers.quzhiyu.graduationproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.quzhiyu.graduationproject.domain.Task;
import pers.quzhiyu.graduationproject.dto.TaskInfo;
import pers.quzhiyu.graduationproject.mapper.CodeMapper;
import pers.quzhiyu.graduationproject.mapper.TaskMapper;
import pers.quzhiyu.graduationproject.service.TaskService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskMapper taskMapper;

    @Autowired
    CodeMapper codeMapper;

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
    @Transactional
    public int deleteTaskById(Long id) {
        //删除开发任务也需要删除对应的测试任务
        taskMapper.deleteTestTaskByTaskId(id);
        //也需要删除对应的源代码文件
        codeMapper.deleteCodeByTaskId(id);
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
        Task t = taskMapper.findTaskById(task.getCodeId());
        t.setId(task.getCodeId());
        t.setStage(3L);
        taskMapper.updateTask(t);
        return taskMapper.insertTask(task);
    }

    @Override
    @Transactional
    public int deleteTestTask(Long testId, Long taskId) {

        Task task = taskMapper.findTaskById(taskId);
        if(task.getStage() == 3) {
            task.setStage(1L);
            taskMapper.updateTask(task);
        }
        return taskMapper.deleteTaskById(testId);
    }

    @Override
    @Transactional
    public int testResult(Task testTask) {
        System.out.println(testTask);
        Task t = taskMapper.findTaskById(testTask.getCodeId());
        t.setStage(testTask.getStage());
        t.setEvaluation(testTask.getEvaluation());

        taskMapper.updateTask(t);
        t = taskMapper.findTaskById(testTask.getId());
        t.setStage(5L);
        return taskMapper.updateTask(t);
    }



    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void checkOverdueTask() {
        System.out.println("checkOverdueTask");
        List<Task> allTask = taskMapper.findAll();
        List<Task> overdueTask = new ArrayList<>();

        for (Task taskInfo : allTask) {
            Timestamp endTime = taskInfo.getEndTime();
            Timestamp currentTime = new Timestamp(new Date().getTime());
            if(endTime.before(currentTime)) {
                overdueTask.add(taskInfo);
            }
        }
        System.out.println(overdueTask);
        taskMapper.checkOverdueTask(overdueTask);

    }

    @Override
    public List<Task> findVisualDataByDate(String begin, String end) {
        return taskMapper.findVisualDataByDate(begin,end);
    }

    @Override
    public List<Task> findStaffVisualDataByDate(String begin, String end, Long id) {
        return taskMapper.findStaffVisualDataByDate(begin,end,id);
    }
}
