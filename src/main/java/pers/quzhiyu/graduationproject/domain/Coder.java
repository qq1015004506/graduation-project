package pers.quzhiyu.graduationproject.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Coder {
    private String username;
    @NotBlank
    private String password;
    private Integer id;

    public Coder(String username, String password, Integer id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public Coder(){

    }
}
