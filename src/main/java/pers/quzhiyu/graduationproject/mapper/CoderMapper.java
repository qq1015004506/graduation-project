package pers.quzhiyu.graduationproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.quzhiyu.graduationproject.domain.Coder;

import java.util.List;
@Mapper
public interface CoderMapper {

    @Select("select * from coder")
    List<Coder> findAll();

    @Select("select * from coder where id=${id}")
    Coder findCoderById(@Param("id") Integer id);
}
