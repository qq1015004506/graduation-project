package pers.quzhiyu.graduationproject.provider;

import org.apache.ibatis.jdbc.SQL;
import pers.quzhiyu.graduationproject.domain.Code;

public class CodeProvider {

    public String updateCode(final Code code) {
        return new SQL(){
            {
                UPDATE("`code`");

                if(code.getFilename() != null)
                    SET("filename = #{filename}");
                if(code.getUploadTime() != null)
                    SET("upload_time = #{uploadTime}");
                if(code.getStaffId() != null)
                    SET("staff_id = #{staffId}");
                if(code.getGroupId() != null)
                    SET("group_id = #{groupId}");
                if(code.getCommit() != null)
                    SET("commit = #{commit}");
                if(code.getVersion() != null)
                    SET("version = #{version}");

                WHERE("id = #{id}");
            }
        }.toString();
    }
}
