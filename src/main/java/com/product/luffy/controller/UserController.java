package com.product.luffy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import com.product.luffy.utils.IdGen;
import com.product.luffy.po.User;
import com.product.luffy.service.UserService;
import com.product.luffy.utils.Exception.ProductRuntimeException;
import com.product.luffy.utils.response.GridResponseObject;
import com.product.luffy.utils.response.HttpResultCode;
import com.product.luffy.utils.response.ResponseObject;
import com.product.luffy.utils.UserContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "com.product.luffy.service.impl.UserService")
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    GridResponseObject<User> selectUserList() {
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

    @RequestMapping(method = RequestMethod.GET, value = "/info")
    public @ResponseBody
    ResponseObject<Boolean> selectUserInfo() {
        ResponseObject
                <Boolean> responseObject = new ResponseObject<Boolean>();
        LOGGER.debug(">>>> selectUserInfo 시작");

        responseObject.setData(userService.selectUserInfo());
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);

        return responseObject;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/selectUserExist")
    public @ResponseBody
    ResponseObject<Boolean> selectUserExist(@RequestParam("userLoginId") String userLoginId,
                                            @RequestParam("email") String email) {
        ResponseObject
                <Boolean> responseObject = new ResponseObject<Boolean>();
        LOGGER.debug(">>>> selectUserExist 시작");
        Boolean result = false;

        if (userLoginId == null || "".equals(userLoginId) || email == null || "".equals(email)) {
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_INVALID_PARAMETER, "로그인ID, email주소가 올바르지 않습니다.");

        }


        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("userLoginId", userLoginId);
        paramMap.put("email", email);
        User user = userService.selectUser(paramMap);

        if (user != null) result = true;

        responseObject.setData(result);
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);

        return responseObject;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public @ResponseBody
    GridResponseObject<User> selectSearchUser(@RequestParam("searchVal") String searchVal) {
        GridResponseObject<User> gridResponseObject = new GridResponseObject<User>();

        //To-do: searchVal cross-side script
        gridResponseObject.setData(userService.selectSearchUser(searchVal));
        gridResponseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);
        return gridResponseObject;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseObject<String> insertUser(@RequestBody Map<String, Object> requestParams) {
        ResponseObject<String> responseObject = new ResponseObject<String>();
        LOGGER.debug(">>>> insertUser 시작" + requestParams);

        String userLoginId = requestParams.get("userLoginId") + "";
        String email = requestParams.get("email") + "";
        String userPwd = requestParams.get("userPwd") + "";
        String userNm = requestParams.get("userNm") + "";

        if (userLoginId == null || "".equals(userLoginId) || email == null || "".equals(email) ||
                userPwd == null || "".equals(userPwd) || userNm == null || "".equals(userNm)
                ) {
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_INVALID_PARAMETER, "파라미터 정보가 올바르지 않습니다.");

        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodingPw = passwordEncoder.encode(userPwd);

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("userId", IdGen.getNextId());
        paramMap.put("userLoginId", userLoginId);
        paramMap.put("email", email);
        paramMap.put("userPwd", encodingPw);
        paramMap.put("userNm", userNm);
        paramMap.put("userRole", "USER");

        User user = userService.selectUser(paramMap);

        if (user != null)
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_INTERNAL_SERVER_EXCEPTION, "중복된 사용자가 있습니다.");

        int rtn = userService.insertUser(paramMap);

        if (rtn != 1)
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_INTERNAL_SERVER_EXCEPTION, "사용자 등록에 실패했습니다.");

        responseObject.setData(rtn);
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);

        return responseObject;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/{userId}")
    public @ResponseBody
    ResponseObject<String> updatetUser(@RequestBody Map<String, Object> requestParams) {
        ResponseObject<String> responseObject = new ResponseObject<String>();
        LOGGER.debug(">>>> updateUser 시작" + requestParams);

        int rtn = 0;

        String userPwd = requestParams.get("userPwd") == null ? null : requestParams.get("userPwd") + "";
        String userNm = requestParams.get("userNm") == null ? null : requestParams.get("userNm") + "";
        String fileId = requestParams.get("fileId") == null ? null : requestParams.get("fileId") + "";

        if ((userPwd == null || "".equals(userPwd)) && (userNm == null || "".equals(userNm))) {
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_INVALID_PARAMETER, "파라미터 정보가 올바르지 않습니다.");
        }

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("userId", UserContext.getUserId());

        if (!(userPwd == null || "".equals(userPwd))) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodingPw = passwordEncoder.encode(userPwd);
            paramMap.put("userPwd", encodingPw);
        }

        if (!(userNm == null || "".equals(userNm))) {
            paramMap.put("userNm", userNm);
        }

        if (!(fileId == null || "".equals(fileId))) {
            paramMap.put("fileId", fileId);
        }

        rtn = userService.updateUser(paramMap);

        if (rtn != 1)
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_INTERNAL_SERVER_EXCEPTION, "사용자 정보수정에 실패하였습니다.");

        responseObject.setData(rtn);
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);

        return responseObject;
    }

}
