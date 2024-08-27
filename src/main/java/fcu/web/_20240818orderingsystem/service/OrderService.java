package fcu.web._20240818orderingsystem.service;

import fcu.web._20240818orderingsystem.model.Order;
import fcu.web._20240818orderingsystem.model.OrderItem;
import fcu.web._20240818orderingsystem.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public abstract class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        logger.info("Fetching all orders");
        List<Order> orders = orderRepository.findAll();
        logger.info("Found {} orders", orders.size());
        return orders;
    }

    public abstract Order createOrder(String userId, List<OrderItem> items);

    public abstract List<Order> getOrdersByUserId(String userId);

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
        // 添加一些示例項目

        return items;
    }
}