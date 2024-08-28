package fcu.web._20240818orderingsystem.service;

import fcu.web._20240818orderingsystem.model.Order;
import fcu.web._20240818orderingsystem.model.OrderItem;
import fcu.web._20240818orderingsystem.model.User;
import fcu.web._20240818orderingsystem.repository.OrderRepository;
import fcu.web._20240818orderingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl extends OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Order createOrder(Long userId, List<OrderItem> items) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderDateTime(LocalDateTime.now());
        order.setUser(user);
        order.setItems(items);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(Order order) {
        if (order.getId() == null) {
            throw new RuntimeException("Order ID cannot be null for update operation");
        }
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getOrdersByUserIdAndStatus(Long userId, String status) {
        return orderRepository.findByUserIdAndStatus(userId, status);
    }

    @Override
    public long countOrdersByUserId(Long userId) {
        return orderRepository.countByUserId(userId);
    }

    // 額外的方法，不在接口中定義
    public Order createOrder(Order order, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(user);
        return orderRepository.save(order);
    }
}