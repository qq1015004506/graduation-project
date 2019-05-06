package pers.quzhiyu.graduationproject.domain;

import lombok.Data;

import java.util.List;

@Data
public class Task {
  private Long id;
  private String name;
  private String description;
  private java.sql.Timestamp startTime;
  private java.sql.Timestamp endTime;
  //工作量
  private Long quantity;
  private Long codeId;
  private Long stage;
  private Long staffId;
  private String evaluation;
  private Long isTest;
}
