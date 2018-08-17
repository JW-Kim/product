package product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import product.model.Auth;
import product.model.AuthGetParam;
import product.service.AuthService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "auth")
public class AuthController {

    @Resource(name = "AuthService")
    private AuthService authService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Auth> selectAuth(){
        System.out.println("controller");
        AuthGetParam authGetParam = new AuthGetParam();
        return authService.selectAuth(authGetParam);
    }
}
