package fcu.web._20240818orderingsystem.service;

import fcu.web._20240818orderingsystem.model.Order;
import fcu.web._20240818orderingsystem.model.OrderItem;
import fcu.web._20240818orderingsystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl extends OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(String userId, List<OrderItem> items) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderDateTime(LocalDateTime.now());
        order.setUserId(userId);
        order.setItems(items);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}