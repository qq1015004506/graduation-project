package pers.quzhiyu.graduationproject.dto;

import lombok.Data;

@Data
public class CoderQueryCondition extends Page {
    private String username;
    private String password;
    private Integer id;
}
