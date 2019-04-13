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
  private String filePath;
  private Long stage;
  private Long staffId;
}
