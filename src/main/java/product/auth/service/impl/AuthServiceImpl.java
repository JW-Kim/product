package product.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.auth.dao.AuthDao;
import product.auth.model.Auth;
import product.auth.model.AuthGetParam;
import product.auth.service.AuthService;

import java.util.List;

@Service(value = "product.auth.service.AuthService")
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthDao authDao;

    public List<Auth> selectAuth(AuthGetParam authGetParam) {
        System.out.println("Service");
        List<Auth> authList = authDao.selectAuth(authGetParam);

        for(int i=0; i<authList.size(); i++){
            System.out.println(authList.get(i).toString());
        }

        return authList;
    }
}
