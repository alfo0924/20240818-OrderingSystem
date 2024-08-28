package fcu.web._20240818orderingsystem.service;

import fcu.web._20240818orderingsystem.model.Order;
import fcu.web._20240818orderingsystem.model.OrderItem;
import fcu.web._20240818orderingsystem.model.User;
import fcu.web._20240818orderingsystem.repository.OrderRepository;
import fcu.web._20240818orderingsystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public Order createOrder(Long userId, List<OrderItem> items) {
        logger.info("Creating order for user {} with {} items", userId, items.size());
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setItems(items);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(user);
        Order savedOrder = orderRepository.save(order);
        logger.info("Order created successfully with ID: {}", savedOrder.getId());
        return savedOrder;
    }

    public List<Order> getOrdersByUserId(Long userId) {
        logger.info("Fetching orders for user {}", userId);
        List<Order> orders = orderRepository.findByUserId(userId);
        logger.info("Found {} orders for user {}", orders.size(), userId);
        return orders;
    }

    public Optional<Order> getOrderById(Long orderId) {
        logger.info("Fetching order with ID: {}", orderId);
        return orderRepository.findById(orderId);
    }

    public List<Order> getAllOrders() {
        logger.info("Fetching all orders");
        List<Order> orders = orderRepository.findAll();
        orders = orders != null ? orders : new ArrayList<>();
        logger.info("Found {} orders", orders.size());
        return orders;
    }

    public Order updateOrder(Order order) {
        logger.info("Updating order with ID: {}", order.getId());
        if (order.getId() == null) {
            throw new RuntimeException("Order ID cannot be null for update operation");
        }
        return orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        logger.info("Deleting order with ID: {}", orderId);
        orderRepository.deleteById(orderId);
    }

    public List<Order> getOrdersByUserIdAndStatus(Long userId, String status) {
        logger.info("Fetching orders for user {} with status {}", userId, status);
        List<Order> orders = orderRepository.findByUserIdAndStatus(userId, status);
        logger.info("Found {} orders for user {} with status {}", orders.size(), userId, status);
        return orders;
    }

    public long countOrdersByUserId(Long userId) {
        logger.info("Counting orders for user {}", userId);
        long count = orderRepository.countByUserId(userId);
        logger.info("Found {} orders for user {}", count, userId);
        return count;
    }

    // 額外的方法，保留原有功能
    public void saveOrder(List<OrderItem> items) {
        logger.info("Saving order with {} items", items.size());
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setItems(items);
        orderRepository.save(order);
        logger.info("Order saved successfully with ID: {}", order.getId());
    }

    // 模擬獲取購物車項目，實際應用中應該從session或數據庫獲取
    public List<OrderItem> getCartItems() {
        List<OrderItem> items = new ArrayList<>();
        // 添加購物車項目的邏輯
        return items;
    }
}