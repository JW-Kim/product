package product.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import product.auth.model.Auth;
import product.auth.model.AuthGetParam;
import product.auth.service.AuthService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "auth")
public class AuthController {

    @Resource(name = "product.auth.service.AuthService")
    private AuthService authService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Auth> selectAuth(){
        System.out.println("controller");
        AuthGetParam authGetParam = new AuthGetParam();
        return authService.selectAuth(authGetParam);
    }
}
