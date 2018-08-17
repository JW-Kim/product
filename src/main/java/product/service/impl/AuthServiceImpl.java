package product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.dao.AuthDao;
import product.model.Auth;
import product.model.AuthGetParam;
import product.service.AuthService;

import java.util.List;

@Service(value = "AuthService")
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthDao authDao;

    public List<Auth> selectAuth(AuthGetParam authGetParam) {
        System.out.println("Service");
        List<Auth> authList = authDao.selectAuth(authGetParam);
        System.out.println("authList.size : "+ authList.size());
        for(int i=0; i<authList.size(); i++){
            System.out.println(authList.get(i).toString());
        }

        return authList;
    }
}
