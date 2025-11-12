package javafx.fe_movie_ticket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, 
                             @RequestParam String password, 
                             HttpSession session, 
                             Model model) {
        // Mock admin credentials
        if ("admin@cineplex.vn".equals(email) && "admin123".equals(password)) {
            // Set admin session
            session.setAttribute("userType", "admin");
            session.setAttribute("userEmail", email);
            session.setAttribute("userName", "Admin User");
            return "redirect:/admin/dashboard";
        }
        // Mock regular user credentials
        else if ("user@example.com".equals(email) && "user123".equals(password)) {
            // Set user session
            session.setAttribute("userType", "user");
            session.setAttribute("userEmail", email);
            session.setAttribute("userName", "Regular User");
            return "redirect:/";
        }
        // Invalid credentials
        else {
            model.addAttribute("error", "Invalid email or password");
            return "auth/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // Admin direct access (for testing)
    @GetMapping("/admin-login")
    public String adminDirectLogin(HttpSession session) {
        // Auto login as admin for testing
        session.setAttribute("userType", "admin");
        session.setAttribute("userEmail", "admin@cineplex.vn");
        session.setAttribute("userName", "Admin User");
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegistration(@RequestParam String fullName,
                                    @RequestParam String email,
                                    @RequestParam String password,
                                    Model model) {
        // Simple validation
        if (email.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
            model.addAttribute("error", "All fields are required");
            return "auth/register";
        }

        // Mock registration success
        model.addAttribute("success", "Registration successful! Please login.");
        return "auth/login";
    }
}
