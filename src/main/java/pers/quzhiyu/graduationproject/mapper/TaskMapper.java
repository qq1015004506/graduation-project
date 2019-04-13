package pers.quzhiyu.graduationproject.mapper;

import org.apache.ibatis.annotations.*;
import pers.quzhiyu.graduationproject.domain.Task;
import pers.quzhiyu.graduationproject.dto.TaskInfo;
import pers.quzhiyu.graduationproject.provider.TaskProvider;

import java.util.List;

@Mapper
public interface TaskMapper {
    @Select("SELECT * FROM `task`")
    List<Task> findAllTask();

    @Select("SELECT * FROM `task` WHERE id=#{id}")
    Task findTaskById(@Param("id") Long id);


    @UpdateProvider(type = TaskProvider.class,method = "updateTask")
    int updateTask(final Task task);

    @Insert("INSERT INTO `task` " +
            "(`name`,`father_id`,`description`,`start_time`,`end_time`,`quantity`,`file_path`,`stage`,`staff_id`,`group_id`) " +
            "VALUES" +
            "(#{name},#{fatherId},#{description},#{startTime},#{endTime},#{quantity},#{filePath},#{stage},#{staffId},#{groupId})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertTask(Task task);

    @Delete("DELETE FROM `task` WHERE id = #{id}")
    int deleteTaskById(@Param("id") Long id);

    @Insert("INSERT INTO `taskinfo` " +
            "(`task_id`,`staff_id`,`group_id`) " +
            "VALUES" +
            "(#{taskId},#{staffId},#{groupId})")
    int distributeTask(TaskInfo taskInfo);

    @UpdateProvider(type = TaskProvider.class, method = "updateTaskInfo")
    int updateTaskInfo(TaskInfo taskInfo);
}
