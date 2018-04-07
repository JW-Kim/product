package product.auth.service;

import product.auth.model.Auth;
import product.auth.model.AuthGetParam;

import java.util.List;

public interface AuthService {
    public List<Auth> selectAuth(AuthGetParam authGetParam);
}
