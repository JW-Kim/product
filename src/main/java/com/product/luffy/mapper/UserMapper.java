package com.product.luffy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.product.luffy.po.User;

@Repository("com.product.luffy.mapper.UserMapper")
public interface UserMapper {
    List<User> selectUserList();

    User selectUser(Map<String, String> paramMap);

    int insertUser(Map<String, String> paramMap);

    int updateUser(Map<String, String> paramMap);

    List<User> selectSearchUser(@Param("searchVal")String searchVal, @Param("userId") String userId);
}


