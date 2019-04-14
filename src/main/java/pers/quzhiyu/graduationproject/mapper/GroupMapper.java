package pers.quzhiyu.graduationproject.mapper;

import org.apache.ibatis.annotations.*;
import pers.quzhiyu.graduationproject.domain.Group;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.domain.Task;
import pers.quzhiyu.graduationproject.dto.*;
import pers.quzhiyu.graduationproject.provider.GroupProvider;

import java.util.List;

@Mapper
public interface GroupMapper {
    @Select("SELECT * FROM `group`")
    @Results({
        @Result(id = true,property = "id",column = "id"),
        @Result(property = "name",column = "name"),
        @Result(property = "leaderId",column = "leader_id"),
        @Result(property = "info",column = "info"),
        @Result(property = "staffs",javaType = List.class,column = "id",
            many = @Many(select = "pers.quzhiyu.graduationproject.mapper.GroupMapper.findStaffs"))
    })
    List<GroupInfo> findAllGroup();

    @Select("SELECT s.*,IFNULL(t.count ,0) count\n" +
            "FROM staff s LEFT JOIN (SELECT staff_id, SUM(quantity) `count` FROM task GROUP BY staff_id) t ON s.id = t.staff_id\n" +
            "WHERE group_id = #{gid}")
    List<StaffCount> findStaffs(Long gid);

    @Select("SELECT * FROM `group` WHERE id=#{id}")
    @Results({
        @Result(id = true,property = "id",column = "id"),
        @Result(property = "name",column = "name"),
        @Result(property = "leaderId",column = "leader_id"),
        @Result(property = "info",column = "info"),
        @Result(property = "staffs",javaType = List.class,column = "id",
                many = @Many(select = "pers.quzhiyu.graduationproject.mapper.GroupMapper.findStaffs"))
    })
    GroupInfo findGroupById(@Param("id") Long id);


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

    @Select("SELECT s.*,g.name groupName\n" +
            "FROM staff s LEFT JOIN `group` g ON s.group_id = g.id")
    List<GroupStaff> findAllGroupStaff();

    //把这个组原有成员的信息清除
    @Update("UPDATE `staff` SET group_id = null WHERE group_id = #{id}")
    void cleanGroupInfoById(Long id);

    @Select("SELECT g.*,gc.count\n" +
            "FROM `group` g LEFT JOIN (SELECT group_id, SUM(quantity) count FROM task GROUP BY group_id) gc\n" +
            "ON g.id = gc.group_id")
    List<GroupCount> findAllGroupCount();

    @Select("SELECT * FROM staff WHERE group_id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "description",column = "description"),
            @Result(property = "quantity",column = "quantity"),
            @Result(property = "stage",column = "stage"),
            @Result(property = "staffId",column = "staff_id"),
            @Result(property = "filePath",column = "file_path"),
            @Result(property = "startTime",column = "start_time"),
            @Result(property = "endTime",column = "end_time"),
            @Result(property = "tasks",javaType = List.class,column = "id",
                    many = @Many(select = "pers.quzhiyu.graduationproject.mapper.GroupMapper.findStaffTasks"))
    })
    List<StaffTask> findAllStaffTask(Long id);

    @Select("SELECT * FROM task WHERE staff_id = #{id}")
    List<Task> findStaffTasks(Long id);
}
