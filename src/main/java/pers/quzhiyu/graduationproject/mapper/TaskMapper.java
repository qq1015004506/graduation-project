package pers.quzhiyu.graduationproject.mapper;

import org.apache.ibatis.annotations.*;
import pers.quzhiyu.graduationproject.domain.Task;
import pers.quzhiyu.graduationproject.dto.TaskInfo;
import pers.quzhiyu.graduationproject.provider.TaskProvider;

import java.util.List;

@Mapper
public interface TaskMapper {
    @Select("SELECT t.*,sg.*\n" +
            "FROM task t LEFT JOIN " +
            "(SELECT s.id staff_id,s.name staff_name,g.name group_name " +
            "FROM staff s LEFT JOIN `group` g " +
            "ON s.group_id = g.id) sg " +
            "ON t.staff_id = sg.staff_id")
    List<TaskInfo> findAllTask();

    @Select("SELECT * FROM task")
    List<Task> findAll();

    @Select("SELECT * FROM `task` WHERE id=#{id}")
    Task findTaskById(@Param("id") Long id);


    @UpdateProvider(type = TaskProvider.class,method = "updateTask")
    int updateTask(final Task task);

    @Insert("INSERT INTO `task` " +
            "(`name`,`description`,`start_time`,`end_time`,`quantity`,`code_id`,`stage`,`staff_id`,`evaluation`,`is_test`) " +
            "VALUES" +
            "(#{name},#{description},#{startTime},#{endTime},#{quantity},#{codeId},#{stage},#{staffId},#{evaluation},#{isTest})")
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

    @Select("SELECT t.*,sg.*\n" +
            "FROM task t LEFT JOIN (SELECT s.id staff_id,s.name staff_name,g.name group_name,g.id group_id FROM staff s LEFT JOIN `group` g ON s.group_id = g.id) sg ON t.staff_id = sg.staff_id\n" +
            "WHERE `name` LIKE '%${name}%' AND `staff_name` LIKE '%${staffName}%' AND group_id = #{group}")
    List<TaskInfo> queryTask(@Param("name") String name,@Param("staffName")  String staffName, @Param("group") Long group);

    @Select("SELECT t.*,sg.*\n" +
            "FROM task t LEFT JOIN (SELECT s.id staff_id,s.name staff_name,g.name group_name,g.id group_id FROM staff s LEFT JOIN `group` g ON s.group_id = g.id) sg ON t.staff_id = sg.staff_id\n" +
            "WHERE `name` LIKE '%${name}%' AND `staff_name` LIKE '%${staffName}%'")
    List<TaskInfo> queryTaskNoGroup(@Param("name") String name,@Param("staffName")  String staffName);

    @Select("SELECT * FROM `task` WHERE staff_id = #{id}")
    List<Task> findTaskByStaffId(Long id);

    @Update("UPDATE `task` set code_id = #{codeId} WHERE id = #{taskId}")
    int changeTaskCodeInfo(@Param("taskId") Long taskId, @Param("codeId") Long codeId);

    @Select("SELECT * FROM `task` WHERE `group_id` = #{id} AND `stage` = 3")
    List<Task> findTaskByGroupForTester(Long id);

    @UpdateProvider(type = TaskProvider.class, method = "checkOverdueTask")
    int checkOverdueTask(@Param("tasks") List<Task> tasks);
    @Select("SELECT * FROM task WHERE start_time BETWEEN #{begin} AND #{end} OR end_time BETWEEN #{begin} AND #{end} ")
    List<Task> findVisualDataByDate(@Param("begin") String begin, @Param("end") String end);

    @Select("SELECT * FROM task t, staff s " +
            "WHERE t.staff_id = s.id AND (t.start_time BETWEEN #{begin} AND #{end} OR t.end_time BETWEEN #{begin} AND #{end}) AND s.id = #{id}")
    List<Task> findStaffVisualDataByDate(@Param("begin") String begin, @Param("end") String end, @Param("id") Long id);
}
