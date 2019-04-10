package pers.quzhiyu.graduationproject.dto;

import lombok.Data;
import pers.quzhiyu.graduationproject.domain.Staff;

@Data
public class GroupStaff extends Staff {
    private String groupName;
}
