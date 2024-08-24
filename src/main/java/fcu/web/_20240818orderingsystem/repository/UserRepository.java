package fcu.web._20240818orderingsystem.repository;

import fcu.web._20240818orderingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}