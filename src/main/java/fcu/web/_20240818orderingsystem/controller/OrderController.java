package fcu.web._20240818orderingsystem.controller;

import fcu.web._20240818orderingsystem.model.Order;
import fcu.web._20240818orderingsystem.model.OrderItem;
import fcu.web._20240818orderingsystem.service.OrderService;
import fcu.web._20240818orderingsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

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
        return "order-list";
    }

//
//    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
//
//    @GetMapping("/ist")
//    public String orderListPage() {
//        logger.info("Accessing order-list page");
//        return "order-list";
//    }


}