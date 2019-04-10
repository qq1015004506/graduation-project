package pers.quzhiyu.graduationproject.dto;

import lombok.Data;

@Data
public class StaffQuery {
    private String name;
    private Long job;
    private int current = 1;
    private int size = 10;
}
