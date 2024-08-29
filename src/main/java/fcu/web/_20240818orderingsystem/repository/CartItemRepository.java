package fcu.web._20240818orderingsystem.repository;

import fcu.web._20240818orderingsystem.model.CartItem;
import fcu.web._20240818orderingsystem.model.Product;
import fcu.web._20240818orderingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);
    Optional<CartItem> findByUserAndProduct(User user, Product product);
    void deleteByUserAndProduct(User user, Product product);
    void deleteByUserId(Long userId);
}