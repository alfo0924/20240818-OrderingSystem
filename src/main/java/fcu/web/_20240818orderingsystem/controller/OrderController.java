package fcu.web._20240818orderingsystem.controller;

import fcu.web._20240818orderingsystem.model.Order;
import fcu.web._20240818orderingsystem.model.OrderItem;
import fcu.web._20240818orderingsystem.service.OrderService;
import fcu.web._20240818orderingsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

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
                              @RequestParam("quantity") List<Integer> quantities) {
        Order order = new Order();
        for (int i = 0; i < productIds.size(); i++) {
            OrderItem item = new OrderItem();
            item.setProduct(productService.getProductById(productIds.get(i)));
            item.setQuantity(quantities.get(i));
            order.addItem(item);
        }
        orderService.createOrder(order);
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
    public String shoppingCart(Model model) {
        logger.info("Accessing shopping cart page");
        // 這裡從session或數據庫獲取購物車項目
        model.addAttribute("cartItems", orderService.getCartItems());
        return "order-car";
    }

    @PostMapping("/submit-order")
    @ResponseBody
    public String submitOrder(@RequestBody List<OrderItem> items) {
        logger.info("Submitting order with {} items", items.size());
        orderService.saveOrder(items);
        logger.info("Order submitted successfully");
        return "Order submitted successfully";
    }

    @GetMapping("/order-history")
    public String orderHistory(Model model) {
        logger.info("Accessing order history page");
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        logger.info("Found {} orders for display", orders.size());
        return "order-history";
    }
}