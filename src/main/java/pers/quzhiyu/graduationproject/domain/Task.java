package pers.quzhiyu.graduationproject.domain;

import lombok.Data;

@Data
public class Task {
  private long id;
  private String name;
  private long fatherId;
  private String description;
  private java.sql.Timestamp startTime;
  private java.sql.Timestamp endTime;
  private long quantity;
  private String filePath;
  private long stage;
}
