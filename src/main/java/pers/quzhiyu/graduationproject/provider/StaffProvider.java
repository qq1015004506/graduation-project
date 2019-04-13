package pers.quzhiyu.graduationproject.provider;

import org.apache.ibatis.jdbc.SQL;
import pers.quzhiyu.graduationproject.domain.Staff;

import java.util.List;

public class StaffProvider {


    public String changeStaffsGroup(List<Staff> staffs,Long newId) {
        StringBuilder res = new StringBuilder();
        res.append("REPLACE INTO `staff` (`id`,`username`,`password`,`name`,`job`,`email`,`phone`,`group_id`) VALUES ");
        for (Staff s : staffs) {
            res.append("("+s.getId()+",'"+s.getUsername()+"','"+s.getPassword()+"','"+s.getName()+"',"+s.getJob()
                    +",'"+s.getEmail()+"','"+s.getPhone()+"',"+newId+"),");
        }
        return res.toString().substring(0,res.length()-1);
    }

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
                if(staff.getGroupId() != null)
                    SET("group_id = #{groupId}");

                WHERE("id = #{id}");
            }
        }.toString();
    }
}
