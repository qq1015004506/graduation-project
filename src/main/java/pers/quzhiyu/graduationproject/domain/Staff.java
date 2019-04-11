package pers.quzhiyu.graduationproject.domain;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class Staff {
  @NotBlank(message = "值不能为空")
  private Long id;
  private String username;
  private String password;
  private String name;
  private Long job;
  private String email;
  private String phone;
  private Long groupId;
}
