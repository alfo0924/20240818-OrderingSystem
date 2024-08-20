package fcu.web._20240818orderingsystem.service;

import fcu.web._20240818orderingsystem.model.User;
import fcu.web._20240818orderingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}