package fcu.web._20240818orderingsystem.controller;

import fcu.web._20240818orderingsystem.model.User;
import fcu.web._20240818orderingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        userService.saveUser(user);
        model.addAttribute("countdown", 3.00);
        return "register-success";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User user = userService.findByUsername(username);
        if (username.isEmpty() || password.isEmpty()) {
            model.addAttribute("error", "請輸入帳號密碼");
            return "login";
        }
        if (user == null) {
            model.addAttribute("error", "無此帳號密碼");
            return "login";
        } else if (!userService.authenticate(username, password)) {
            model.addAttribute("error", "帳號/密碼輸入錯誤，請重新輸入！");
            return "login";
        } else {
            session.setAttribute("loggedIn", true);
            session.setAttribute("username", username);
            return "redirect:/order";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    @GetMapping("/order")
    public String orderPage(HttpSession session) {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if (loggedIn != null && loggedIn) {
            return "order";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/member")
    public String memberArea(HttpSession session, Model model) {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if (loggedIn != null && loggedIn) {
            String username = (String) session.getAttribute("username");
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
            return "member";
        } else {
            return "redirect:/login";
        }
    }


    @GetMapping("/member/edit")
    public String showEditMemberForm(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "edit-member";
    }

    @PostMapping("/member/update")
    public String updateMember(@ModelAttribute User user, @RequestParam(required = false) String newPassword, HttpSession session) {
        User existingUser = userService.findByUsername((String) session.getAttribute("username"));
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        if (newPassword != null && !newPassword.isEmpty()) {
            existingUser.setPassword(newPassword); // 注意：實際應用中應該對密碼進行加密
        }
        userService.updateUser(existingUser);
        session.setAttribute("username", existingUser.getUsername());
        return "redirect:/member";
    }
    @GetMapping("/member/order-history")
    public String showOrderHistory(HttpSession session, Model model) {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if (loggedIn != null && loggedIn) {
            // 這裡可以添加獲取用戶訂單歷史的邏輯
            // 例如：List<Order> orders = orderService.getOrdersByUsername((String) session.getAttribute("username"));
            // model.addAttribute("orders", orders);
            return "order-history";
        } else {
            return "redirect:/login";
        }
    }

}