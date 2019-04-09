package pers.quzhiyu.graduationproject.provider;

import org.apache.ibatis.jdbc.SQL;
import pers.quzhiyu.graduationproject.domain.Task;
import pers.quzhiyu.graduationproject.dto.TaskInfo;

public class TaskProvider {

    public String updateTaskInfo(final TaskInfo taskInfo) {
        return new SQL(){
            {
                UPDATE("taskinfo");

                if(taskInfo.getTaskId() != null)
                    SET("task_id = #{taskId}");
                if(taskInfo.getGroupId() != null)
                    SET("group_id = #{groupId}");
                if(taskInfo.getStaffId() != null)
                    SET("staff_id = #{staffId}");

                WHERE("id = #{id}");
            }
        }.toString();
    }

    public String updateTask(final Task task) {
        return new SQL(){
            {
                UPDATE("task");

                if(task.getName() != null)
                    SET("name = #{name}");
                if(task.getFatherId() != null)
                    SET("father_id = #{fatherId}");
                if(task.getDescription() != null)
                    SET("description = #{description}");
                if(task.getStartTime() != null)
                    SET("start_time = #{startTime}");
                if(task.getEndTime() != null)
                    SET("end_time = #{endTime}");
                if(task.getQuantity() != null)
                    SET("quantity = #{quantity}");
                if(task.getFilePath() != null)
                    SET("file_path = #{filePath}");
                if(task.getStage() != null)
                    SET("stage = #{stage}");

                WHERE("id = #{id}");
            }
        }.toString();
    }
}
