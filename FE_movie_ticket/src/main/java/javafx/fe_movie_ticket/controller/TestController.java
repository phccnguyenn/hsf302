package javafx.fe_movie_ticket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class TestController {

    @GetMapping("/test")
    public String testPage(HttpSession session, Model model) {
        String userType = (String) session.getAttribute("userType");
        String userName = (String) session.getAttribute("userName");
        String userEmail = (String) session.getAttribute("userEmail");
        
        model.addAttribute("userType", userType != null ? userType : "Not logged in");
        model.addAttribute("userName", userName != null ? userName : "Guest");
        model.addAttribute("userEmail", userEmail != null ? userEmail : "No email");
        
        return "test-page";
    }

    @GetMapping("/test-info")
    public String testInfo(Model model) {
        // Mock admin credentials
        model.addAttribute("adminEmail", "admin@cineplex.vn");
        model.addAttribute("adminPassword", "admin123");
        
        // Mock user credentials  
        model.addAttribute("userEmail", "user@example.com");
        model.addAttribute("userPassword", "user123");
        
        // Additional test accounts
        model.addAttribute("managerEmail", "manager@cineplex.vn");
        model.addAttribute("managerPassword", "manager123");
        
        model.addAttribute("staffEmail", "staff@cineplex.vn");
        model.addAttribute("staffPassword", "staff123");
        
        return "test-info";
    }

    @GetMapping("/admin-test")  
    public String adminTest() {
        return "redirect:/admin/dashboard";
    }
}