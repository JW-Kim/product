package com.product.luffy.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.product.luffy.po.User;

@Repository("com.product.luffy.mapper.UserMapper")
public interface UserMapper {
	List<User> selectUserList();
	
	User selectUser(Map<String, String> paramMap);
}
