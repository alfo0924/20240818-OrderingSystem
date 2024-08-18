package fcu.web._20240818orderingsystem.controller;

import fcu.web._20240818orderingsystem.model.Order;
import fcu.web._20240818orderingsystem.model.OrderItem;
import fcu.web._20240818orderingsystem.service.OrderService;
import fcu.web._20240818orderingsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

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

    @GetMapping("/list")
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "order-list";
    }
}