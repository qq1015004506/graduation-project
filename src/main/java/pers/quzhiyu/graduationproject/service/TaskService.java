package pers.quzhiyu.graduationproject.service;

import org.springframework.stereotype.Service;
import pers.quzhiyu.graduationproject.domain.Task;
import pers.quzhiyu.graduationproject.dto.TaskInfo;

import java.util.List;

@Service
public interface TaskService {
    List<Task> findAllTask();

    Task findTaskById(Long id);

    int updateTask(final Task task);

    int insertTask(Task task);

    int deleteTaskById(Long id);

    int distributeTask(TaskInfo taskInfo);

    int updateTaskInfo(TaskInfo taskInfo);
}
