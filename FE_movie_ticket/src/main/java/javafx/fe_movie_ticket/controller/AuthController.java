package javafx.fe_movie_ticket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLoginPage() {
        // This will return the templates/login.html file
        // (or templates/auth/login.html if you used a subfolder)
        return "auth/login";
    }

    // --- REGISTER METHODS (NEW) ---
    @GetMapping("/register")
    public String showRegisterPage() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegistration() {
        // For now, we will keep it simple.
        // 1. Receive the form data (Full Name, Email, Password).
        // 2. Validate the data (e.g., check if the email is already in use).
        // 3. Encrypt the password.
        // 4. Save the new user to the database.

        // After successful registration, redirect the user to the login page.
        return "redirect:/login";
    }
}
