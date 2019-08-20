package com.product.luffy.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.product.luffy.po.User;
import com.product.luffy.service.UserService;
import com.product.luffy.utils.UserContext;
import com.product.luffy.utils.Exception.ProductRuntimeException;
import com.product.luffy.utils.response.HttpResultCode;
import com.product.luffy.utils.response.ResponseObject;


@Controller
public class LoginController {

    @Resource(name = "com.product.luffy.service.impl.UserService")
    private UserService userService;

    @RequestMapping("/login")
    public @ResponseBody
    ResponseObject<Object> login(HttpServletRequest request,
                                 @RequestParam("username") String username,
                                 @RequestParam("password") String password) throws Exception {
        ResponseObject<Object> responseObject = new ResponseObject<Object>();

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("userLoginId", username);
        User user = userService.selectUser(paramMap);
        if(user == null) {
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_INVALID_PARAMETER, "아이디 및 비밀번호가 일치하지 않습니다.");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String token = "";
        //비밀번호가 동일하면
        if (user.getUserPwd() != null && passwordEncoder.matches(password, user.getUserPwd())) {
            try {
                Algorithm algorithm = Algorithm.HMAC256("luffy-jw4005.kim");
                token = JWT.create()
                        .withIssuer("auth0")
                        .withClaim("userloginId", "jw4005.kim")
                        .withClaim("userId", user.getUserId())
                        .withClaim("userNm", user.getUserNm())
                        .withClaim("userRole", user.getUserRole())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000))
                        .sign(algorithm);
            } catch (JWTCreationException exception) {
                throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN,"토큰생성에 실패했습니다.");
            }

        } else {
            throw new ProductRuntimeException(HttpResultCode.PRODUCT_INVALID_PARAMETER, "아이디 및 비밀번호가 일치하지 않습니다.");
        }

        responseObject.setData(token);
        responseObject.setResultCode(HttpResultCode.PRODUCT_SUCCESS);

        return responseObject;
    }

    @RequestMapping("/logout")
    public @ResponseBody
    ResponseObject<Object> logout(HttpSession session) throws Exception {
        ResponseObject<Object> responseObject = new ResponseObject<Object>();
        System.out.println(">>>>>>>>>>>>>>> logout" + UserContext.getUserNm());
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsInVzZXJsb2dpbklkIjoianc0MDA1LmtpbSIsImV4cCI6MTU0Mjc5ODIwN30.HJbQ76v241Ub1ouEHiuKOEwu5gE9QSBogfsTmwHfzsc";

        try {
            Algorithm algorithm = Algorithm.HMAC256("luffy-jw4005.kim");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            Claim userloginId = jwt.getClaim("userloginId");

        } catch (JWTVerificationException exception) {
            System.out.println(">>>>>>>>>>>>>>> JWTVerificationException" + exception);
        }

        return responseObject;
    }
}
