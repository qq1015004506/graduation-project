package pers.quzhiyu.graduationproject.dto;

import lombok.Data;
import pers.quzhiyu.graduationproject.domain.Group;
import pers.quzhiyu.graduationproject.domain.Staff;

import java.util.List;

@Data
public class GroupInfo extends Group {
    List<Staff> staffs;
}
