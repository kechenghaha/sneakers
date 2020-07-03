package kecheng.haha.sneakers.mapper;

import kecheng.haha.sneakers.model.Information;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InformationMapper {
    @Insert("insert into information (title,tag,description,creator,gmt_create,gmt_modified) values (#{title},#{tag},#{description},#{creator},#{gmtCreate},#{gmtModified})")
    void create(Information information);

    @Select("select * from information limit #{offset},#{size}")
    List<Information> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from information")
    Integer count();

    @Select("select * from information where creator = #{userId} limit #{offset},#{size} ")
    List<Information> listByUserId(@Param(value = "userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from information where creator = #{userId}")
    Integer countByUserId(@Param(value = "userId") Integer userId);
}
