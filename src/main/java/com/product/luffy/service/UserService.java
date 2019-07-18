package com.product.luffy.service;

import java.util.List;
import java.util.Map;

import com.product.luffy.po.User;

public interface UserService {
    public List<User> selectUserList();

    public User selectUser(Map<String, String> paramMap);

    public User selectUserInfo();

    public int insertUser(Map<String, String> paramMap);

    public int updateUser(Map<String, String> paramMap);

    public List<User> selectSearchUser(String searchVal);
}
