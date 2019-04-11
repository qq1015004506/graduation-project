package pers.quzhiyu.graduationproject.mapper;

import org.apache.ibatis.annotations.*;
import pers.quzhiyu.graduationproject.domain.Staff;
import pers.quzhiyu.graduationproject.provider.StaffProvider;

import java.util.List;
@Mapper
public interface StaffMapper {
    @Select("SELECT * FROM `staff`")
    List<Staff> findAll();

    @Select("SELECT * FROM `staff` WHERE id=#{id}")
    Staff findStaffById(@Param("id") Long id);


    @UpdateProvider(type = StaffProvider.class,method = "updateStaff")
    int updateStaff(final Staff staff);

    @Insert("INSERT INTO `staff` " +
            "(`username`,`password`,`name`,`job`,`email`,`phone`,`group_id`) " +
            "VALUES" +
            "(#{username},#{password},#{name},#{job},#{email},#{phone},#{group_id})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertStaff(Staff staff);

    @Delete("DELETE FROM `staff` WHERE id = #{id}")
    int deleteStaffById(@Param("id") Long id);

    @Select("SELECT * FROM `staff` WHERE job = #{job} and name like '%${name}%'")
    List<Staff> queryStaff(@Param("name") String name, @Param("job") Long job);
    @Select("SELECT * FROM `staff` WHERE job = #{job}")
    List<Staff> queryStaffByJob(@Param("job") Long job);
    @Select("SELECT * FROM `staff` WHERE name like '%${name}%'")
    List<Staff> queryStaffByName(@Param("name") String name);

    @UpdateProvider(type = StaffProvider.class, method = "changeStaffsGroup")
    int changeStaffsGroup(List<Staff> staffs,Long newId);
}
