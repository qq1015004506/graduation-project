package pers.quzhiyu.graduationproject.provider;

import org.apache.ibatis.jdbc.SQL;
import pers.quzhiyu.graduationproject.domain.Task;
import pers.quzhiyu.graduationproject.dto.TaskInfo;

public class TaskProvider {

    public String updateTaskInfo(final TaskInfo taskInfo) {
        return new SQL(){

        }.toString();
    }

    public String updateTask(final Task task) {
        return new SQL(){
            {
                UPDATE("task");

                if(task.getName() != null)
                    SET("name = #{name}");
                if(task.getDescription() != null)
                    SET("description = #{description}");
                if(task.getStartTime() != null)
                    SET("start_time = #{startTime}");
                if(task.getEndTime() != null)
                    SET("end_time = #{endTime}");
                if(task.getQuantity() != null)
                    SET("quantity = #{quantity}");
                if(task.getCodeId() != null)
                    SET("code_id = #{codeId}");
                if(task.getStage() != null)
                    SET("stage = #{stage}");
                if(task.getStaffId() != null)
                    SET("staff_id = #{staffId}");

                WHERE("id = #{id}");
            }
        }.toString();
    }
}
