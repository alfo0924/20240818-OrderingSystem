package fcu.web._20240818orderingsystem.service;

import fcu.web._20240818orderingsystem.model.User;
import fcu.web._20240818orderingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        try {
            if (userRepository.findByUsername("admin") == null) {
                User adminUser = new User("admin", "admin@example.com", "admin", "0123456789", true);
                userRepository.save(adminUser);
            }
        } catch (Exception e) {
            System.err.println("Error initializing admin user: " + e.getMessage());
        }
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}