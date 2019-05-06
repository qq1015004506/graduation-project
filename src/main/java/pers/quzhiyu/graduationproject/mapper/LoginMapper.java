package pers.quzhiyu.graduationproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.quzhiyu.graduationproject.domain.Staff;

@Mapper
public interface LoginMapper {
    @Select("SELECT * FROM `staff` WHERE `username` = #{username} And `password` = #{password}")
    Staff login(@Param("username") String username, @Param("password") String password);
}
