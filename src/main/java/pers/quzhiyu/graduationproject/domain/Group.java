package pers.quzhiyu.graduationproject.domain;

import lombok.Data;

@Data
public class Group {

  private Long id;
  private String name;
  private Long leaderId;
  private String info;


}
