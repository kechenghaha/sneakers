package kecheng.haha.sneakers.mapper;

import kecheng.haha.sneakers.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modified,avatar_url) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@RequestParam(name = "token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@RequestParam(name = "id") Integer id);
}
