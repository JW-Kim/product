package com.product.luffy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.product.luffy.mapper.UserMapper;
import com.product.luffy.po.User;
import com.product.luffy.service.UserService;

@Service("com.product.luffy.service.impl.UserService")
public class UserServiceImpl implements UserService{
	
	@Resource(name="com.product.luffy.mapper.UserMapper")
	private UserMapper userMapper;
	
	public List<User> selectUserList(){
		
		List<User> userList = userMapper.selectUserList();
		
		for(int i=0; i<userList.size(); i++) {
			System.out.println(">>>>>>>>>>"+userList.get(i).toString());
		}
		
		return userList;
	}
}
