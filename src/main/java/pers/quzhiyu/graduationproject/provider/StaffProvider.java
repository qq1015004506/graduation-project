package pers.quzhiyu.graduationproject.provider;

import org.apache.ibatis.jdbc.SQL;
import pers.quzhiyu.graduationproject.domain.Staff;

public class StaffProvider {

    public String queryStaff(String name,Long job) {
        return new SQL(){
            {
                SELECT("*");
                FROM("staff");
                if(name != null)
                    WHERE("name like '%${name}%'");
                AND();
                if(job != null)
                    WHERE("job = #{job}");
            }
        }.toString();
    }

    public String updateStaff(final Staff staff) {
        return new SQL(){
            {
                UPDATE("staff");

                if(staff.getUsername() != null)
                    SET("username = #{username}");
                if(staff.getPassword() != null)
                    SET("password = #{password}");
                if(staff.getName() != null)
                    SET("name = #{name}");
                if(staff.getJob() != null)
                    SET("job = #{job}");
                if(staff.getEmail() != null)
                    SET("email = #{email}");
                if(staff.getPhone() != null)
                    SET("phone = #{phone}");

                WHERE("id = #{id}");
            }
        }.toString();
    }
}
