package fcu.web._20240818orderingsystem.service;

import fcu.web._20240818orderingsystem.model.Order;
import fcu.web._20240818orderingsystem.model.OrderItem;
import fcu.web._20240818orderingsystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public abstract class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public abstract Order createOrder(String userId, List<OrderItem> items);

    public abstract List<Order> getOrdersByUserId(String userId);

    public void saveOrder(List<OrderItem> items) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setItems(items);
        orderRepository.save(order);
    }
}