package javafx.fe_movie_ticket.controller;

import javafx.fe_movie_ticket.entity.Order;
import javafx.fe_movie_ticket.entity.enumeration.OrderStatus;
import javafx.fe_movie_ticket.service.OrderService;
import javafx.fe_movie_ticket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    // --- Show order creation form ---
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("customers", userService.getAllUsers().stream()
                .filter(u -> "CUSTOMER".equals(u.getRole())).toList());
        return "order_create"; // thymeleaf template
    }

    // --- Create order ---
    @PostMapping("/create")
    public String createOrder(@ModelAttribute Order order, @RequestParam Long customerId, Model model) {
        order.setCustomer(userService.getUserById(customerId));
        order.setStatus(OrderStatus.PENDING);
        order.setTicketCode(UUID.randomUUID().toString());
        orderService.createOrder(order);
        model.addAttribute("message", "Order created successfully!");
        return "redirect:/orders/list";
    }

    // --- List orders ---
    @GetMapping("/list")
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "order_list"; // thymeleaf template
    }

    // --- Update order status ---
    @PostMapping("/update-status")
    public String updateStatus(@RequestParam Long orderId, @RequestParam String status, Model model) {
        orderService.updateOrderStatus(orderId, OrderStatus.valueOf(status));
        return "redirect:/orders/list";
    }

    // --- View order by ticket code ---
    @GetMapping("/view")
    public String viewOrder(@RequestParam String ticketCode, Model model) {
        Order order = orderService.getByTicketCode(ticketCode)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        model.addAttribute("order", order);
        return "order_view"; // thymeleaf template
    }
}
