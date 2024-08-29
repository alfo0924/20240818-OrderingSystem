package fcu.web._20240818orderingsystem.controller;

import fcu.web._20240818orderingsystem.model.Order;
import fcu.web._20240818orderingsystem.model.OrderItem;
import fcu.web._20240818orderingsystem.model.Product;
import fcu.web._20240818orderingsystem.service.OrderService;
import fcu.web._20240818orderingsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping("/create")
    public String createOrder(@RequestParam("productId") List<Long> productIds,
                              @RequestParam("quantity") List<Integer> quantities,
                              HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        List<OrderItem> items = new ArrayList<>();
        for (int i = 0; i < productIds.size(); i++) {
            OrderItem item = new OrderItem();
            item.setProduct(productService.getProductById(productIds.get(i)));
            item.setQuantity(quantities.get(i));
            items.add(item);
        }

        orderService.createOrder(userId, items);
        return "redirect:/orders/success";
    }

    @GetMapping("/success")
    public String orderSuccess() {
        return "order-success";
    }

    @GetMapping("/order")
    public String orderPage() {
        return "order";
    }

    @GetMapping("/order-list")
    public String orderListPage() {
        logger.info("Accessing order-list page");
        return "order-list";
    }

    @GetMapping("/cart")
    public String shoppingCart(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        Map<Long, List<Product>> userCarts = (Map<Long, List<Product>>) session.getAttribute("userCarts");
        List<Product> cart = userCarts != null ? userCarts.getOrDefault(userId, new ArrayList<>()) : new ArrayList<>();
        model.addAttribute("cartItems", cart);
        return "order-car";
    }

    @PostMapping("/submit-order")
    @ResponseBody
    public ResponseEntity<String> submitOrder(@RequestBody List<OrderItem> items, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.badRequest().body("User not logged in");
        }

        try {
            orderService.createOrder(userId, items);
            return ResponseEntity.ok("Order submitted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Order submission failed: " + e.getMessage());
        }
    }

    @GetMapping("/test-order-submission")
    @ResponseBody
    public String testOrderSubmission() {
        return "Order submission endpoint is working";
    }

    @GetMapping("/order-history")
    public String orderHistory(Model model, HttpSession session) {
        logger.info("Accessing order history page");
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        List<Order> orders = orderService.getOrdersByUserId(userId);
        model.addAttribute("orders", orders != null ? orders : new ArrayList<>());
        logger.info("Found {} orders for display", orders != null ? orders.size() : 0);
        return "order-history";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Test endpoint working";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam Long productId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        Map<Long, List<Product>> userCarts = (Map<Long, List<Product>>) session.getAttribute("userCarts");
        if (userCarts == null) {
            userCarts = new HashMap<>();
            session.setAttribute("userCarts", userCarts);
        }

        List<Product> cart = userCarts.getOrDefault(userId, new ArrayList<>());
        Product product = productService.getProductById(productId);
        cart.add(product);
        userCarts.put(userId, cart);
        return "redirect:/orders/cart";
    }
}