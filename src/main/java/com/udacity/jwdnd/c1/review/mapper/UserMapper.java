package com.udacity.jwdnd.c1.review.mapper;

import com.udacity.jwdnd.c1.review.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USERS ( username, salt, password, firstname, lastname)" +
            "VALUES (#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    int createUser(User user);


    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    //@Delete("DELETE * FROM USERS WHERE id = {id}")
    //int deleteUser(User user);


}
