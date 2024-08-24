package fcu.web._20240818orderingsystem.controller;

import fcu.web._20240818orderingsystem.model.Order;
import fcu.web._20240818orderingsystem.model.OrderItem;
import fcu.web._20240818orderingsystem.service.OrderService;
import fcu.web._20240818orderingsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody List<OrderItem> items, @RequestParam String userId) {
        try {
            Order order = orderService.createOrder(userId, items);
            logger.info("Order created successfully for user: " + userId);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            logger.error("Error creating order: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrderWithProductIds(@RequestParam("productId") List<Long> productIds,
                                                           @RequestParam("quantity") List<Integer> quantities,
                                                           @RequestParam String userId) {
        try {
            Order order = new Order();
            for (int i = 0; i < productIds.size(); i++) {
                OrderItem item = new OrderItem();
                item.setProduct(productService.getProductById(productIds.get(i)));
                item.setQuantity(quantities.get(i));
                order.addItem(item);
            }
            Order createdOrder = orderService.createOrder(userId, order.getItems());
            logger.info("Order created successfully with product IDs for user: " + userId);
            return ResponseEntity.ok(createdOrder);
        } catch (Exception e) {
            logger.error("Error creating order with product IDs: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(@RequestParam String userId) {
        try {
            List<Order> orders = orderService.getOrdersByUserId(userId);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            logger.error("Error retrieving orders: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/success")
    public ResponseEntity<String> orderSuccess() {
        return ResponseEntity.ok("Order placed successfully");
    }

    @GetMapping("/order")
    public ResponseEntity<String> orderPage() {
        return ResponseEntity.ok("Order page");
    }

    @GetMapping("/order-list")
    public ResponseEntity<String> orderListPage() {
        logger.info("Accessing order-list page");
        return ResponseEntity.ok("Order list page");
    }

    @GetMapping("/cart")
    public ResponseEntity<String> shoppingCart() {
        logger.info("Accessing shopping cart page");
        return ResponseEntity.ok("Shopping cart page");
    }
}