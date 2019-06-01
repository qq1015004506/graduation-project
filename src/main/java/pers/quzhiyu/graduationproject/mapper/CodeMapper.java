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
            "(filename,upload_time,staff_id,task_id,commit,code_detail)" +
            "VALUES" +
            "(#{filename},#{uploadTime},#{staffId},#{taskId},#{commit},#{codeDetail})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertCode(Code code);

    @UpdateProvider(type = CodeProvider.class,method = "updateCode")
    int updateCode(Code code);

    @Delete("DELETE FROM `code` WHERE id = #{id}")
    int deleteCodeById(Long id);

    @Select("SELECT * FROM `code` WHERE task_id = #{id}")
    List<Code> findAllCodeByTaskId(Long id);
    @Delete("DELETE FROM `code` WHERE task_id = #{id}")
    void deleteCodeByTaskId(Long id);
}
