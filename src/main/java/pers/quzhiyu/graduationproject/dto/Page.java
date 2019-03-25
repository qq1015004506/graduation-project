package pers.quzhiyu.graduationproject.dto;

import lombok.Data;

@Data
public class Page {
    private Integer size = 10;
    private Integer page = 1;
    private String sort = "id asc";
}
