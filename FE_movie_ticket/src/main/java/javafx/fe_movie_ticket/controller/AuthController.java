package javafx.fe_movie_ticket.controller;

import javafx.fe_movie_ticket.entity.User;
import javafx.fe_movie_ticket.entity.enumeration.Role;
import javafx.fe_movie_ticket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, 
                             @RequestParam String password, 
                             HttpSession session, 
                             Model model) {
        
        // Check if user exists in database
        Optional<User> userOptional = userService.findByEmail(email);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            // Simple password check (in production, use password encoder)
            if (password.equals(user.getPassword()) && user.isActive()) {
                // Set user session
                session.setAttribute("userId", user.getId());
                session.setAttribute("userType", user.getRole().toString().toLowerCase());
                session.setAttribute("userEmail", user.getEmail());
                session.setAttribute("userName", user.getFullName());
                
                // Redirect based on role
                if (user.getRole() == Role.STAFF || user.getRole() == Role.ADMIN) {
                    return "redirect:/admin/dashboard";
                } else {
                    return "redirect:/";
                }
            }
        }
        
        // Invalid credentials
        model.addAttribute("error", "Invalid email or password");
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegistration(@RequestParam String fullName,
                                    @RequestParam String email,
                                    @RequestParam String password,
                                    Model model,
                                    RedirectAttributes redirectAttributes) {
        
        // Validation
        if (email == null || email.trim().isEmpty() || 
            password == null || password.trim().isEmpty() || 
            fullName == null || fullName.trim().isEmpty()) {
            model.addAttribute("error", "All fields are required");
            model.addAttribute("fullName", fullName);
            model.addAttribute("email", email);
            return "auth/register";
        }
        
        // Check email format
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            model.addAttribute("error", "Please enter a valid email address");
            model.addAttribute("fullName", fullName);
            model.addAttribute("email", email);
            return "auth/register";
        }
        
        // Check password length
        if (password.length() < 6) {
            model.addAttribute("error", "Password must be at least 6 characters long");
            model.addAttribute("fullName", fullName);
            model.addAttribute("email", email);
            return "auth/register";
        }
        
        // Check if email already exists
        if (userService.existsByEmail(email)) {
            model.addAttribute("error", "Email address already exists");
            model.addAttribute("fullName", fullName);
            model.addAttribute("email", email);
            return "auth/register";
        }
        
        try {
            // Create new user
            User newUser = new User();
            newUser.setFullName(fullName.trim());
            newUser.setEmail(email.trim().toLowerCase());
            newUser.setPassword(password); // TODO: Hash password in production
            newUser.setRole(Role.CUSTOMER); // Default role
            newUser.setActive(true);
            
            // Save user
            userService.createUser(newUser);
            
            // Success message
            redirectAttributes.addFlashAttribute("success", 
                "Registration successful! Please login with your credentials.");
            return "redirect:/login";
            
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed. Please try again.");
            model.addAttribute("fullName", fullName);
            model.addAttribute("email", email);
            return "auth/register";
        }
    }
    
    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        if (session.getAttribute("userName") == null) {
            return "redirect:/login";
        }
        
        // Thêm thông tin user vào model
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));
        model.addAttribute("userType", session.getAttribute("userType"));
        
        return "user/profile";
    }
    
    @GetMapping("/bookings")
    public String showBookings(HttpSession session, Model model) {
        if (session.getAttribute("userName") == null) {
            return "redirect:/login";
        }
        
        // Mock booking data for now
        model.addAttribute("userName", session.getAttribute("userName"));
        return "user/bookings";
    }
}
