package pers.quzhiyu.graduationproject.domain;

import lombok.Data;

// 文件具有版本控制功能
//

@Data
public class Code {
    private Long id;
    private String filename;
    private java.sql.Timestamp uploadTime;
    private Long staffId;
    //相同文件共用同一个组id
    private Long taskId;
    private String commit;
}
