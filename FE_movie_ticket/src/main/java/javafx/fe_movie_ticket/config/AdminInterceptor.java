package javafx.fe_movie_ticket.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String uri = request.getRequestURI();
        
        // Skip interceptor for login and static resources
        if (uri.contains("/login") || uri.contains("/admin-login") || 
            uri.contains("/static") || uri.contains("/css") || 
            uri.contains("/js") || uri.contains("/images")) {
            return true;
        }
        
        // Check if accessing admin pages
        if (uri.startsWith("/admin")) {
            HttpSession session = request.getSession(false);
            
            if (session == null || !"admin".equals(session.getAttribute("userType"))) {
                // Not logged in as admin, redirect to login
                response.sendRedirect("/login?error=Please login as admin to access this area");
                return false;
            }
        }
        
        return true;
    }
}