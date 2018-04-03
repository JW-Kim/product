package product.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @RequestMapping(value = "/")
    public String test(){
        System.out.println("test!!!!!!!!!!!!!!!!!!!");
        return "index";
    }
}
