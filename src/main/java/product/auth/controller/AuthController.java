package product.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import product.auth.model.AuthGetParam;
import product.auth.service.AuthService;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "auth")
public class AuthController {

    @Resource(name = "product.auth.service.AuthService")
    private AuthService authService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Object selectAuth(){
        System.out.println("controller");
        AuthGetParam authGetParam = new AuthGetParam();

        return authService.selectAuth(authGetParam);
    }
}
