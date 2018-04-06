package product.auth.service;

import product.auth.model.AuthGetParam;

public interface AuthService {
    public Object selectAuth(AuthGetParam authGetParam);
}
