package pers.quzhiyu.graduationproject.dto;

import lombok.Data;
import pers.quzhiyu.graduationproject.domain.Staff;

@Data
public class StaffCount extends Staff {
    private Long count;
}
