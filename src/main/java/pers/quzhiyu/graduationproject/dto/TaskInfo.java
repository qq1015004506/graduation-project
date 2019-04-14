package pers.quzhiyu.graduationproject.dto;

import lombok.Data;
import pers.quzhiyu.graduationproject.domain.Task;


@Data
public class TaskInfo extends Task {
    private String staffName;
    private String groupName;
}
