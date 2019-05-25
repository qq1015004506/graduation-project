package pers.quzhiyu.graduationproject.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import pers.quzhiyu.graduationproject.domain.Task;
import pers.quzhiyu.graduationproject.dto.TaskInfo;

import java.util.List;

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
                if(task.getEvaluation() != null)
                    SET("evaluation = #{evaluation}");
                if(task.getIsTest() != null)
                    SET("is_test = #{isTest}");

                WHERE("id = #{id}");
            }
        }.toString();
    }

    public String checkOverdueTask(@Param("tasks") List<Task> tasks) {
        System.out.println("....checkOverdueTask");
        StringBuilder res = new StringBuilder();
        res.append("REPLACE INTO `task` (`id`,`name`,`description`,`start_time`,`end_time`,`quantity`,`code_id`,`stage`,`staff_id`,`evaluation`,`is_test`) VALUES ");
        for (Task t : tasks) {
            res.append("("+t.getId()+",'"+t.getName()+"','"+t.getDescription()+"','"+t.getStartTime()+"','"+t.getEndTime()
                    +"',"+t.getQuantity()+","+t.getCodeId()+",6,"+t.getStaffId()+",'"+t.getEvaluation()+"',"+t.getIsTest()+"),");
        }
        return res.toString().substring(0,res.length()-1);
    }
}
