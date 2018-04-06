package product.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.auth.dao.AuthDao;
import product.auth.model.AuthGetParam;
import product.auth.service.AuthService;

@Service(value = "product.auth.service.AuthService")
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthDao authDao;

    public Object selectAuth(AuthGetParam authGetParam) {
        System.out.println("Service");
        return authDao.selectAuth(authGetParam);
    }
}
