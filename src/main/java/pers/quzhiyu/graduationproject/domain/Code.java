package pers.quzhiyu.graduationproject.domain;

import lombok.Data;

@Data
public class Code {

  private long id;
  private String filename;
  private java.sql.Timestamp uploadTime;
  private long staffId;
  private long fatherId;
  private long realId;
  private long version;

}
