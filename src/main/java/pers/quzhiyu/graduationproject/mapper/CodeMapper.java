package pers.quzhiyu.graduationproject.mapper;

import org.apache.ibatis.annotations.*;
import pers.quzhiyu.graduationproject.domain.Code;
import pers.quzhiyu.graduationproject.provider.CodeProvider;

import java.util.List;
@Mapper
public interface CodeMapper {

    @Select("SELECT * FROM `code` WHERE id = #{id}")
    Code findCodeById(Long id);
    @Select("SELECT * FROM `code`")
    List<Code> findAllCode();
    @Insert("INSERT INTO `code`" +
            "(filename,upload_time,staff_id,group_id,commit,version)" +
            "VALUES" +
            "(#{filename},#{uploadTime},#{staffId},#{groupId},#{commit},#{version})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertCode(Code code);

    @InsertProvider(type = CodeProvider.class,method = "updateCode")
    int updateCode(Code code);

    @Delete("DELETE FROM `code` WHERE id = #{id}")
    int deleteCodeById(Long id);
}
