package fcu.web._20240818orderingsystem.controller;

import fcu.web._20240818orderingsystem.model.Order;
import fcu.web._20240818orderingsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "create-order";
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute Order order) {
        orderService.createOrder(order);
        return "redirect:/orders/list";
    }

    @GetMapping("/list")
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "order-list";
    }
}