package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE userid=#{userId}")
    User findById(Integer userId);

    @Select("SELECT * FROM USERS WHERE username=#{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES (#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    int insert(User user);

    @Delete("DELETE FROM USERS WHERE userid=#{userId}")
    void delete(Integer userId);
}
