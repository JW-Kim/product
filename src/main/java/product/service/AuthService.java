package product.service;

import product.model.Auth;
import product.model.AuthGetParam;

import java.util.List;

public interface AuthService {
    public List<Auth> selectAuth(AuthGetParam authGetParam);
}
