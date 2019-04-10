package pers.quzhiyu.graduationproject.mapper;

import org.apache.ibatis.annotations.*;
import pers.quzhiyu.graduationproject.domain.Group;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.dto.GroupStaff;
import pers.quzhiyu.graduationproject.provider.GroupProvider;

import java.util.List;

@Mapper
public interface GroupMapper {
    @Select("SELECT * FROM `group`")
    List<Group> findAllGroup();

    @Select("SELECT * FROM `group` WHERE id=#{id}")
    Group findGroupById(@Param("id") Long id);


    @UpdateProvider(type = GroupProvider.class,method = "updateGroup")
    int updateGroup(final Group group);

    @Insert("INSERT INTO `group` " +
            "(`name`,`leader_id`,`info`) " +
            "VALUES" +
            "(#{name},#{leaderId},#{info})")

    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertGroup(Group group);

    @Delete("DELETE FROM `group` WHERE id = #{id}")
    int deleteGroupById(@Param("id") Long id);


    List<Staff> findMemberByGroupId(Long id);

    @InsertProvider(type = GroupProvider.class,method = "addMemberToGroup")
    int addMemberToGroup(Long id, List<Long> groupMember);


    @DeleteProvider(type = GroupProvider.class,method = "deleteMemberFromGroup")
    int deleteMemberFromGroup(Long id, List<Long> ids);

    @Select("SELECT s.*,c.name groupName\n" +
            "FROM staff s LEFT JOIN (SELECT * FROM `group` g, groupinfo gi WHERE g.id = gi.group_id) c ON s.id = c.staff_id")
    List<GroupStaff> findAllGroupStaff();
}
