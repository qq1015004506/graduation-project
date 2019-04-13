package pers.quzhiyu.graduationproject.dto;

import lombok.Data;
import pers.quzhiyu.graduationproject.domain.Group;

@Data
public class GroupCount extends Group {
    private Long count;
}
