package pers.quzhiyu.graduationproject.provider;

import org.apache.ibatis.jdbc.SQL;
import pers.quzhiyu.graduationproject.domain.Group;

import java.util.List;

public class GroupProvider {
    public String updateGroup(final Group group) {
        return new SQL(){
            {
                UPDATE("`group`");

                if(group.getName() != null)
                    SET("name = #{name}");
                if(group.getLeaderId() != null)
                    SET("leader_id = #{leaderId}");
                if(group.getInfo() != null)
                    SET("info = #{info}");

                WHERE("id = #{id}");
            }
        }.toString();
    }

    public String addMemberToGroup(Long id, List<Long> groupMember) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `groupinfo`\n");
        sb.append("(group_id, staff_id)\n");
        sb.append("VALUES\n");
        for(Long mid : groupMember) {
            sb.append("(" + id + ", " + mid + "), ");
        }
        return sb.subSequence(0,sb.length()-2).toString();
    }

    public String deleteMemberFromGroup(Long id, List<Long> ids) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM `groupinfo`\n");
        sb.append("WHERE `group_id` = " + id + " AND `staff_id` IN (");
        for (Long i : ids) {
            sb.append(i+",");
        }
        return sb.subSequence(0,sb.length()-1).toString() + ")";
    }
}
