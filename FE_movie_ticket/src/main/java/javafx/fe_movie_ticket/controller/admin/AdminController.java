package javafx.fe_movie_ticket.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("")
    public String adminRedirect() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Mock data for dashboard
        model.addAttribute("todayRevenue", 15750000.0);
        model.addAttribute("weekRevenue", 89250000.0);
        model.addAttribute("monthRevenue", 456800000.0);
        model.addAttribute("todayTickets", 342);
        model.addAttribute("averageOccupancy", 78.5);
        model.addAttribute("totalCinemas", 5);
        model.addAttribute("totalScreens", 32);
        model.addAttribute("activeMovies", 12);
        
        // Top movies data
        List<Map<String, Object>> topMovies = createTopMoviesData();
        model.addAttribute("topMovies", topMovies);
        
        // Revenue chart data (last 7 days)
        List<Map<String, Object>> revenueChart = createRevenueChartData();
        model.addAttribute("revenueChart", revenueChart);
        
        // Recent bookings
        List<Map<String, Object>> recentBookings = createRecentBookingsData();
        model.addAttribute("recentBookings", recentBookings);
        
        return "admin/dashboard";
    }

    @GetMapping("/movies")
    public String movieManagement(Model model) {
        // This will be handled by AdminMovieController
        return "redirect:/admin/movies/list";
    }

    @GetMapping("/bookings")
    public String bookingManagement(Model model) {
        List<Map<String, Object>> todayBookings = createTodayBookingsData();
        model.addAttribute("todayBookings", todayBookings);
        
        model.addAttribute("totalBookingsToday", todayBookings.size());
        model.addAttribute("confirmedBookings", 
            (int) todayBookings.stream().filter(b -> "confirmed".equals(b.get("status"))).count());
        model.addAttribute("cancelledBookings", 
            (int) todayBookings.stream().filter(b -> "cancelled".equals(b.get("status"))).count());
        
        return "admin/booking-management";
    }

    @PostMapping("/bookings/{id}/confirm")
    @ResponseBody
    public Map<String, Object> confirmBooking(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Mock booking confirmation
            System.out.println("Confirming booking: " + id);
            response.put("success", true);
            response.put("message", "Đặt vé đã được xác nhận");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Có lỗi xảy ra khi xác nhận đặt vé");
        }
        return response;
    }

    @PostMapping("/bookings/{id}/cancel")
    @ResponseBody
    public Map<String, Object> cancelBooking(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Mock booking cancellation
            System.out.println("Cancelling booking: " + id);
            response.put("success", true);
            response.put("message", "Đặt vé đã được hủy");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Có lỗi xảy ra khi hủy đặt vé");
        }
        return response;
    }

    @PostMapping("/bookings/{id}/refund")
    @ResponseBody
    public Map<String, Object> refundBooking(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Mock booking refund
            System.out.println("Processing refund for booking: " + id);
            response.put("success", true);
            response.put("message", "Hoàn tiền đã được xử lý");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Có lỗi xảy ra khi xử lý hoàn tiền");
        }
        return response;
    }

    @PostMapping("/bookings/bulk-action")
    @ResponseBody
    public Map<String, Object> bulkBookingAction(@RequestParam String action,
                                                @RequestParam List<String> bookingIds) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("Bulk action '" + action + "' for bookings: " + bookingIds);
            
            switch (action) {
                case "confirm":
                    response.put("success", true);
                    response.put("message", "Đã xác nhận " + bookingIds.size() + " đặt vé");
                    break;
                case "cancel":
                    response.put("success", true);
                    response.put("message", "Đã hủy " + bookingIds.size() + " đặt vé");
                    break;
                case "export":
                    response.put("success", true);
                    response.put("message", "Đang xuất báo cáo cho " + bookingIds.size() + " đặt vé");
                    break;
                default:
                    response.put("success", false);
                    response.put("message", "Hành động không hợp lệ");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Có lỗi xảy ra");
        }
        return response;
    }

    @GetMapping("/reports")
    public String reports(Model model) {
        // Revenue by cinema
        List<Map<String, Object>> cinemaRevenue = createCinemaRevenueData();
        model.addAttribute("cinemaRevenue", cinemaRevenue);
        
        // Revenue by movie
        List<Map<String, Object>> movieRevenue = createMovieRevenueData();
        model.addAttribute("movieRevenue", movieRevenue);
        
        return "admin/reports";
    }

    @PostMapping("/reports/generate")
    @ResponseBody
    public Map<String, Object> generateReport(@RequestParam String reportType,
                                             @RequestParam String period,
                                             @RequestParam(required = false) String format) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("Generating " + reportType + " report for " + period + " in " + format);
            response.put("success", true);
            response.put("message", "Báo cáo đang được tạo. Bạn sẽ nhận được thông báo khi hoàn thành.");
            response.put("reportId", "RPT" + System.currentTimeMillis());
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Có lỗi xảy ra khi tạo báo cáo");
        }
        return response;
    }

    @PostMapping("/reports/export")
    @ResponseBody
    public Map<String, Object> exportReport(@RequestParam String format) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("Exporting report in " + format + " format");
            response.put("success", true);
            response.put("message", "Đang xuất báo cáo...");
            response.put("downloadUrl", "/admin/downloads/report." + format.toLowerCase());
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Có lỗi xảy ra khi xuất báo cáo");
        }
        return response;
    }

    @GetMapping("/users")
    public String userManagement(Model model) {
        List<Map<String, Object>> users = createUsersData();
        
        model.addAttribute("users", users);
        model.addAttribute("totalUsers", users.size());
        model.addAttribute("activeUsers", 
            (int) users.stream().filter(u -> "ACTIVE".equals(u.get("status"))).count());
        model.addAttribute("newUsersToday", 3);
        model.addAttribute("premiumUsers", 
            (int) users.stream().filter(u -> "VIP".equals(u.get("role"))).count());
        
        return "admin/user-management";
    }

    @PostMapping("/users/{id}/toggle-status")
    @ResponseBody
    public Map<String, Object> toggleUserStatus(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Mock user status toggle
            System.out.println("Toggling status for user: " + id);
            response.put("success", true);
            response.put("message", "Trạng thái người dùng đã được cập nhật");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Có lỗi xảy ra");
        }
        return response;
    }

    @GetMapping("/settings")
    public String settings(Model model) {
        // Mock settings data
        Map<String, Object> settings = new HashMap<>();
        settings.put("cinemaName", "CGV Cinemas Vietnam");
        settings.put("hotline", "1900 6017");
        settings.put("address", "L3-CT4, Nha Trang Centre, 01 Tran Hung Dao, Loc Tho Ward, Nha Trang City");
        settings.put("contactEmail", "contact@cgv.vn");
        settings.put("website", "https://www.cgv.vn");
        settings.put("bookingHoldTime", 15);
        settings.put("baseTicketPrice", 75000);
        settings.put("weekendSurcharge", 20);
        settings.put("holidaySurcharge", 30);
        settings.put("allowOnlinePayment", true);
        settings.put("requireMemberLogin", false);
        settings.put("smtpServer", "smtp.gmail.com");
        settings.put("smtpPort", 587);
        settings.put("fromEmail", "noreply@cgv.vn");
        settings.put("emailPassword", "••••••••");
        settings.put("enableEmailNotification", true);
        
        model.addAttribute("settings", settings);
        return "admin/settings";
    }

    @PostMapping("/settings/update")
    public String updateSettings(@RequestParam Map<String, String> settingsData,
                                RedirectAttributes redirectAttributes) {
        try {
            // Mock settings update
            System.out.println("Updating settings: " + settingsData);
            redirectAttributes.addFlashAttribute("success", "Cài đặt đã được cập nhật");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi cập nhật cài đặt");
        }
        return "redirect:/admin/settings";
    }

    // Mock data creation methods
    private List<Map<String, Object>> createUsersData() {
        List<Map<String, Object>> users = new ArrayList<>();
        
        Map<String, Object> user1 = new HashMap<>();
        user1.put("id", 1);
        user1.put("username", "nguyenvana");
        user1.put("fullName", "Nguyễn Văn A");
        user1.put("email", "nguyenvana@gmail.com");
        user1.put("phone", "0901234567");
        user1.put("role", "CUSTOMER");
        user1.put("status", "ACTIVE");
        user1.put("createdDate", "08/11/2025");
        user1.put("avatar", "/images/default-avatar.png");
        users.add(user1);
        
        Map<String, Object> user2 = new HashMap<>();
        user2.put("id", 2);
        user2.put("username", "tranthib");
        user2.put("fullName", "Trần Thị B");
        user2.put("email", "tranthib@gmail.com");
        user2.put("phone", "0912345678");
        user2.put("role", "VIP");
        user2.put("status", "ACTIVE");
        user2.put("createdDate", "09/11/2025");
        user2.put("avatar", "/images/default-avatar.png");
        users.add(user2);
        
        Map<String, Object> user3 = new HashMap<>();
        user3.put("id", 3);
        user3.put("username", "levanc");
        user3.put("fullName", "Lê Văn C");
        user3.put("email", "levanc@gmail.com");
        user3.put("phone", "0923456789");
        user3.put("role", "CUSTOMER");
        user3.put("status", "LOCKED");
        user3.put("createdDate", "07/11/2025");
        user3.put("avatar", "/images/default-avatar.png");
        users.add(user3);
        
        Map<String, Object> user4 = new HashMap<>();
        user4.put("id", 4);
        user4.put("username", "admin");
        user4.put("fullName", "Administrator");
        user4.put("email", "admin@cgv.vn");
        user4.put("phone", "1900 6017");
        user4.put("role", "ADMIN");
        user4.put("status", "ACTIVE");
        user4.put("createdDate", "01/01/2025");
        user4.put("avatar", "/images/admin-avatar.png");
        users.add(user4);
        
        return users;
    }
    private List<Map<String, Object>> createTopMoviesData() {
        List<Map<String, Object>> movies = new ArrayList<>();
        
        Map<String, Object> movie1 = new HashMap<>();
        movie1.put("title", "Spider-Man: No Way Home");
        movie1.put("ticketsSold", 1250);
        movie1.put("revenue", 3750000.0);
        movie1.put("poster", "https://via.placeholder.com/100x150.png/E50914/FFFFFF?text=SPIDER-MAN");
        movies.add(movie1);
        
        Map<String, Object> movie2 = new HashMap<>();
        movie2.put("title", "Dune: Part Two");
        movie2.put("ticketsSold", 980);
        movie2.put("revenue", 3136000.0);
        movie2.put("poster", "https://via.placeholder.com/100x150.png/1F1F1F/FFFFFF?text=DUNE");
        movies.add(movie2);
        
        Map<String, Object> movie3 = new HashMap<>();
        movie3.put("title", "Fast X");
        movie3.put("ticketsSold", 756);
        movie3.put("revenue", 2494800.0);
        movie3.put("poster", "https://via.placeholder.com/100x150.png/333333/FFFFFF?text=FAST+X");
        movies.add(movie3);
        
        return movies;
    }

    private List<Map<String, Object>> createRevenueChartData() {
        List<Map<String, Object>> data = new ArrayList<>();
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        double[] revenues = {12500000, 8750000, 9200000, 11800000, 15600000, 22400000, 18900000};
        
        for (int i = 0; i < days.length; i++) {
            Map<String, Object> day = new HashMap<>();
            day.put("day", days[i]);
            day.put("revenue", revenues[i]);
            data.add(day);
        }
        
        return data;
    }

    private List<Map<String, Object>> createRecentBookingsData() {
        List<Map<String, Object>> bookings = new ArrayList<>();
        
        Map<String, Object> booking1 = new HashMap<>();
        booking1.put("id", "TK1234");
        booking1.put("customerName", "Nguyen Van A");
        booking1.put("movie", "Spider-Man: No Way Home");
        booking1.put("cinema", "CGV Vincom Center");
        booking1.put("time", "19:30");
        booking1.put("amount", 240000.0);
        booking1.put("status", "confirmed");
        bookings.add(booking1);
        
        Map<String, Object> booking2 = new HashMap<>();
        booking2.put("id", "TK1235");
        booking2.put("customerName", "Tran Thi B");
        booking2.put("movie", "Dune: Part Two");
        booking2.put("cinema", "CGV Landmark 81");
        booking2.put("time", "21:00");
        booking2.put("amount", 320000.0);
        booking2.put("status", "confirmed");
        bookings.add(booking2);
        
        return bookings;
    }

    private List<Map<String, Object>> createTodayBookingsData() {
        List<Map<String, Object>> bookings = createRecentBookingsData();
        
        // Add more mock bookings
        Map<String, Object> booking3 = new HashMap<>();
        booking3.put("id", "TK1236");
        booking3.put("customerName", "Le Van C");
        booking3.put("customerPhone", "0909123456");
        booking3.put("movie", "Fast X");
        booking3.put("cinema", "CGV Bitexco");
        booking3.put("time", "15:30");
        booking3.put("amount", 180000.0);
        booking3.put("status", "pending");
        booking3.put("seats", "B5, B6");
        booking3.put("paymentMethod", "Cash");
        bookings.add(booking3);
        
        Map<String, Object> booking4 = new HashMap<>();
        booking4.put("id", "TK1237");
        booking4.put("customerName", "Nguyen Thi D");
        booking4.put("customerPhone", "0912345678");
        booking4.put("movie", "Spider-Man: No Way Home");
        booking4.put("cinema", "CGV Diamond Plaza");
        booking4.put("time", "13:15");
        booking4.put("amount", 200000.0);
        booking4.put("status", "cancelled");
        booking4.put("seats", "C3, C4");
        booking4.put("paymentMethod", "Card");
        bookings.add(booking4);
        
        return bookings;
    }

    private List<Map<String, Object>> createCinemaRevenueData() {
        List<Map<String, Object>> cinemas = new ArrayList<>();
        
        Map<String, Object> cinema1 = new HashMap<>();
        cinema1.put("id", 1);
        cinema1.put("name", "CGV Vincom Center");
        cinema1.put("address", "72 Le Thanh Ton, District 1, HCMC");
        cinema1.put("screens", 8);
        cinema1.put("status", "active");
        cinema1.put("todayRevenue", 4250000.0);
        cinemas.add(cinema1);
        
        Map<String, Object> cinema2 = new HashMap<>();
        cinema2.put("id", 2);
        cinema2.put("name", "CGV Landmark 81");
        cinema2.put("address", "Landmark 81, Binh Thanh, HCMC");
        cinema2.put("screens", 12);
        cinema2.put("status", "active");
        cinema2.put("todayRevenue", 6800000.0);
        cinemas.add(cinema2);
        
        Map<String, Object> cinema3 = new HashMap<>();
        cinema3.put("id", 3);
        cinema3.put("name", "CGV Bitexco");
        cinema3.put("address", "Bitexco Financial Tower, District 1, HCMC");
        cinema3.put("screens", 10);
        cinema3.put("status", "active");
        cinema3.put("todayRevenue", 3850000.0);
        cinemas.add(cinema3);
        
        return cinemas;
    }

    private List<Map<String, Object>> createMovieRevenueData() {
        return createTopMoviesData();
    }
}
