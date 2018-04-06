package product.auth.dao;

import org.springframework.stereotype.Repository;
import product.auth.model.AuthGetParam;

@Repository(value = "produce.auth.dao.AuthDao")
public class AuthDao {

    public Object selectAuth(AuthGetParam authGetParam){
        System.out.println("dao");
        return null;
    }
}
