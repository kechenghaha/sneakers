package kecheng.haha.sneakers.mapper;

import kecheng.haha.sneakers.model.Information;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InformationMapper {
    @Insert("insert into information (title,tag,description,creator,gmt_create,gmt_modified) values (#{title},#{tag},#{description},#{creator},#{gmtCreate},#{gmtModified})")
    void create(Information information);
}
