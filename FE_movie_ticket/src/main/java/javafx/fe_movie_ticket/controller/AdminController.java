package javafx.fe_movie_ticket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin") // All URLs in this controller will start with /admin
public class AdminController {

    @GetMapping("/dashboard")
    public String showAdminDashboard() {
        // This tells Thymeleaf to find the file at:
        // src/main/resources/templates/admin/dashboard.html
        return "admin/dashboard";
    }
}
