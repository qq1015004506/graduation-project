package pers.quzhiyu.graduationproject.dto;

import lombok.Data;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.domain.Task;

import java.util.List;
@Data
public class StaffTask extends Staff {
    List<Task> tasks;
}
