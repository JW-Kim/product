package com.product.luffy.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.product.luffy.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Resource(name="com.product.luffy.service.impl.UserService")
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Object selectUserList() {
		Object object = new Object();
		System.out.println(">>>> selectUserList 시작");
		userService.selectUserList();
		System.out.println(">>>> selectUserList 끝");
		return object;
	}
}
