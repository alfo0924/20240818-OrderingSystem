package fcu.web._20240818orderingsystem.service;

import fcu.web._20240818orderingsystem.model.User;

public interface UserService {
    User saveUser(User user);
    User findByUsername(String username);
    boolean authenticate(String username, String password);
}