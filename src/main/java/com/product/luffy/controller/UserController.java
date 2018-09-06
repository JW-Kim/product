package com.product.luffy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.product.luffy.po.User;
import com.product.luffy.service.UserService;
import com.product.luffy.utils.Exception.ProductRuntimeException;
import com.product.luffy.utils.response.GridResponseObject;
import com.product.luffy.utils.response.HttpResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("user")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="com.product.luffy.service.impl.UserService")
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody GridResponseObject<User> selectUserList() {
		GridResponseObject<User> gridResponseObject = new GridResponseObject<User>();
		LOGGER.debug(">>>> selectUserList 시작");
		
		List<User> userList = userService.selectUserList();
		
/*		if(userList.size() == 2) {
			throw new ProductRuntimeException(HttpResultCode.PRODUCT_CONFLICT, "test");
		}*/
		gridResponseObject.setData(userList);
		gridResponseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
		System.out.println(">>>> selectUserList 끝");
		return gridResponseObject;
	}
}
