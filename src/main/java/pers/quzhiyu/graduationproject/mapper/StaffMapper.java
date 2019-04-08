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

    @Select("SELECT * FROM `staff` WHERE job = 1")
    List<Staff> findAllManager();

    @Select("SELECT * FROM `staff` WHERE job = 2")
    List<Staff> findAllCoder();

    @Select("SELECT * FROM `staff` WHERE job = 3")
    List<Staff> findAllTester();

    @UpdateProvider(type = StaffProvider.class,method = "updateStaff")
    int updateStaff(final Staff staff);

    @Insert("INSERT INTO `staff` " +
            "(`username`,`password`,`name`,`job`,`email`,`phone`) " +
            "VALUES" +
            "(#{username},#{password},#{name},#{job},#{email},#{phone})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertStaff(Staff staff);

    @Delete("DELETE FROM `staff` WHERE id = #{id}")
    int deleteStaffById(@Param("id") Long id);

}
