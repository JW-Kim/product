package com.product.luffy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.luffy.utils.response.HttpResultCode;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.product.luffy.po.Auth;
import com.product.luffy.utils.UserContext;
import com.product.luffy.utils.Exception.ProductRuntimeException;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * Intercept the execution of a handler. Called after HandlerMapping determined an
     * appropriate handler object, but before HandlerAdapter invokes the handler.
     */
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        System.out.println(">>>>>>>>>>>>>>>>>>>> LoginInterceptor preHandle...");
        String requestURL = request.getRequestURI();

        if ("/product/login".equals(requestURL) || "/product/user".equals(requestURL) || "/product/user/selectUserExist".equals(requestURL)) {

        } else {
            String authorization = request.getHeader("authorization");
            if ("Bearer".equals(authorization.split(" ")[0])) {
                String token = authorization.split(" ")[1];
                try {
                    Algorithm algorithm = Algorithm.HMAC256("luffy-jw4005.kim");
                    JWTVerifier verifier = JWT.require(algorithm)
                            .withIssuer("auth0")
                            .build();
                    DecodedJWT jwt = verifier.verify(token);
                    Claim userId = jwt.getClaim("userId");
                    Claim userloginId = jwt.getClaim("userloginId");
                    Claim userNm = jwt.getClaim("userNm");
                    Claim userRole = jwt.getClaim("userRole");

                    Auth auth = new Auth();
                    auth.setUserId(userId.asString());
                    auth.setUserLoginId(userloginId.asString());
                    auth.setUserNm(userNm.asString());
                    auth.setUserRole(userRole.asString());

                    UserContext.put(auth);

                } catch (JWTVerificationException exception) {
                    throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "인증에 실패하였습니다.");
                }
            } else {
                throw new ProductRuntimeException(HttpResultCode.PRODUCT_FORBIDDEN, "인증에 실패하였습니다.");
            }
        }

        return super.preHandle(request, response, handler);
    }

    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {

        System.out.println("LoginInterceptor postHandle...");
    }


    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {

        System.out.println("LoginInterceptor afterCompletion...");
    }
}
