package javafx.fe_movie_ticket.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/movies")
public class AdminMovieController {

    @GetMapping("/list")
    public String listMovies(Model model) {
        List<Map<String, Object>> movies = createMockMoviesList();
        model.addAttribute("movies", movies);
        return "admin/movie-list";
    }

    @GetMapping("/add")
    public String addMovieForm(Model model) {
        return "admin/movie-form";
    }

    @PostMapping("/add")
    public String addMovie(@RequestParam Map<String, String> movieData, 
                          RedirectAttributes redirectAttributes) {
        // Mock movie creation
        String title = movieData.get("title");
        
        // Validate required fields
        if (title == null || title.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Tên phim là bắt buộc");
            return "redirect:/admin/movies/add";
        }
        
        // Simulate saving movie to database
        System.out.println("Creating new movie: " + title);
        
        redirectAttributes.addFlashAttribute("success", "Phim '" + title + "' đã được thêm thành công");
        return "redirect:/admin/movies/list";
    }

    @GetMapping("/edit/{id}")
    public String editMovieForm(@PathVariable Long id, Model model) {
        Map<String, Object> movie = createMockMovie(id);
        if (movie.isEmpty()) {
            return "redirect:/admin/movies/list";
        }
        model.addAttribute("movie", movie);
        return "admin/movie-form";
    }

    @PostMapping("/edit/{id}")
    public String updateMovie(@PathVariable Long id,
                             @RequestParam Map<String, String> movieData,
                             RedirectAttributes redirectAttributes) {
        String title = movieData.get("title");
        
        if (title == null || title.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Tên phim là bắt buộc");
            return "redirect:/admin/movies/edit/" + id;
        }
        
        // Simulate updating movie in database
        System.out.println("Updating movie ID " + id + ": " + title);
        
        redirectAttributes.addFlashAttribute("success", "Phim '" + title + "' đã được cập nhật thành công");
        return "redirect:/admin/movies/list";
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String, Object> deleteMovie(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Simulate movie deletion
            System.out.println("Deleting movie ID: " + id);
            
            // Check if movie exists and can be deleted
            Map<String, Object> movie = createMockMovie(id);
            if (movie.isEmpty()) {
                response.put("success", false);
                response.put("message", "Không tìm thấy phim");
                return response;
            }
            
            // Check if movie has active bookings (mock check)
            boolean hasActiveBookings = id == 1L; // Simulate that movie ID 1 has bookings
            if (hasActiveBookings) {
                response.put("success", false);
                response.put("message", "Không thể xóa phim có đặt vé đang hoạt động");
                return response;
            }
            
            response.put("success", true);
            response.put("message", "Phim đã được xóa thành công");
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Có lỗi xảy ra khi xóa phim");
        }
        
        return response;
    }

    @GetMapping("/schedule/{id}")
    public String movieSchedule(@PathVariable Long id, Model model) {
        Map<String, Object> movie = createMockMovie(id);
        List<Map<String, Object>> showtimes = createMockShowtimes(id);
        
        model.addAttribute("movie", movie);
        model.addAttribute("showtimes", showtimes);
        return "admin/movie-schedule";
    }

    @PostMapping("/schedule/{id}")
    public String addShowtime(@PathVariable Long id,
                             @RequestParam Map<String, String> showtimeData,
                             RedirectAttributes redirectAttributes) {
        // Mock showtime creation
        String cinema = showtimeData.get("cinema");
        String date = showtimeData.get("date");
        String time = showtimeData.get("time");
        
        System.out.println("Adding showtime for movie " + id + ": " + cinema + " at " + date + " " + time);
        
        redirectAttributes.addFlashAttribute("success", "Suất chiếu đã được thêm thành công");
        return "redirect:/admin/movies/schedule/" + id;
    }

    @GetMapping("/analytics/{id}")
    public String movieAnalytics(@PathVariable Long id, Model model) {
        Map<String, Object> movie = createMockMovie(id);
        Map<String, Object> analytics = createMockAnalytics(id);
        
        model.addAttribute("movie", movie);
        model.addAttribute("analytics", analytics);
        return "admin/movie-analytics";
    }

    @PostMapping("/bulk-action")
    @ResponseBody
    public Map<String, Object> bulkAction(@RequestParam String action,
                                         @RequestParam List<Long> movieIds) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            switch (action) {
                case "delete":
                    System.out.println("Bulk deleting movies: " + movieIds);
                    response.put("success", true);
                    response.put("message", movieIds.size() + " phim đã được xóa");
                    break;
                case "status":
                    System.out.println("Bulk status update for movies: " + movieIds);
                    response.put("success", true);
                    response.put("message", "Trạng thái đã được cập nhật cho " + movieIds.size() + " phim");
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

    // Mock data creation methods
    private List<Map<String, Object>> createMockMoviesList() {
        List<Map<String, Object>> movies = new ArrayList<>();
        
        Map<String, Object> movie1 = new HashMap<>();
        movie1.put("id", 1L);
        movie1.put("title", "Spider-Man: No Way Home");
        movie1.put("genre", "Action, Adventure");
        movie1.put("duration", 148);
        movie1.put("releaseDate", "15/12/2021");
        movie1.put("status", "NOW_SHOWING");
        movie1.put("rating", "PG-13");
        movie1.put("poster", "https://via.placeholder.com/300x450.png/E50914/FFFFFF?text=SPIDER-MAN");
        movie1.put("revenue", 3750000.0);
        movie1.put("ticketsSold", 1250);
        movie1.put("director", "Jon Watts");
        movie1.put("cast", "Tom Holland, Zendaya, Benedict Cumberbatch");
        movie1.put("synopsis", "Với danh tính Spider-Man được tiết lộ, Peter nhờ Doctor Strange giúp đỡ...");
        movies.add(movie1);
        
        Map<String, Object> movie2 = new HashMap<>();
        movie2.put("id", 2L);
        movie2.put("title", "Dune: Part Two");
        movie2.put("genre", "Sci-Fi, Adventure");
        movie2.put("duration", 166);
        movie2.put("releaseDate", "01/03/2024");
        movie2.put("status", "NOW_SHOWING");
        movie2.put("rating", "PG-13");
        movie2.put("poster", "https://via.placeholder.com/300x450.png/1F1F1F/FFFFFF?text=DUNE");
        movie2.put("revenue", 3136000.0);
        movie2.put("ticketsSold", 980);
        movie2.put("director", "Denis Villeneuve");
        movie2.put("cast", "Timothée Chalamet, Zendaya, Rebecca Ferguson");
        movie2.put("synopsis", "Paul Atreides thống nhất với Chani và người Fremen...");
        movies.add(movie2);
        
        Map<String, Object> movie3 = new HashMap<>();
        movie3.put("id", 3L);
        movie3.put("title", "Fast X");
        movie3.put("genre", "Action, Crime");
        movie3.put("duration", 141);
        movie3.put("releaseDate", "19/05/2023");
        movie3.put("status", "COMING_SOON");
        movie3.put("rating", "PG-13");
        movie3.put("poster", "https://via.placeholder.com/300x450.png/333333/FFFFFF?text=FAST+X");
        movie3.put("revenue", 2494800.0);
        movie3.put("ticketsSold", 756);
        movie3.put("director", "Louis Leterrier");
        movie3.put("cast", "Vin Diesel, Michelle Rodriguez, Tyrese Gibson");
        movie3.put("synopsis", "Dom Toretto và gia đình đối mặt với mối đe dọa lớn nhất...");
        movies.add(movie3);
        
        return movies;
    }
    
    private Map<String, Object> createMockMovie(Long id) {
        List<Map<String, Object>> allMovies = createMockMoviesList();
        return allMovies.stream()
                .filter(movie -> movie.get("id").equals(id))
                .findFirst()
                .orElse(new HashMap<>());
    }
    
    private List<Map<String, Object>> createMockShowtimes(Long movieId) {
        List<Map<String, Object>> showtimes = new ArrayList<>();
        
        Map<String, Object> showtime1 = new HashMap<>();
        showtime1.put("id", 1L);
        showtime1.put("cinema", "CGV Vincom Center");
        showtime1.put("screen", "Screen 1");
        showtime1.put("time", "19:30");
        showtime1.put("date", "2024-11-15");
        showtime1.put("format", "2D");
        showtime1.put("occupancy", 85.5);
        showtime1.put("availableSeats", 45);
        showtime1.put("totalSeats", 180);
        showtimes.add(showtime1);
        
        Map<String, Object> showtime2 = new HashMap<>();
        showtime2.put("id", 2L);
        showtime2.put("cinema", "CGV Landmark 81");
        showtime2.put("screen", "Screen 2");
        showtime2.put("time", "21:00");
        showtime2.put("date", "2024-11-15");
        showtime2.put("format", "3D");
        showtime2.put("occupancy", 92.0);
        showtime2.put("availableSeats", 15);
        showtime2.put("totalSeats", 200);
        showtimes.add(showtime2);
        
        return showtimes;
    }
    
    private Map<String, Object> createMockAnalytics(Long movieId) {
        Map<String, Object> analytics = new HashMap<>();
        analytics.put("totalRevenue", 3750000.0);
        analytics.put("totalTickets", 1250);
        analytics.put("averageOccupancy", 78.5);
        analytics.put("topCinema", "CGV Vincom Center");
        analytics.put("peakHour", "19:30-21:30");
        analytics.put("demographics", Map.of(
            "age_18_25", 35,
            "age_26_35", 42,
            "age_36_45", 18,
            "age_46_plus", 5
        ));
        
        // Daily revenue for the last 7 days
        List<Map<String, Object>> dailyRevenue = new ArrayList<>();
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        double[] revenues = {450000, 520000, 380000, 610000, 750000, 890000, 720000};
        
        for (int i = 0; i < days.length; i++) {
            Map<String, Object> day = new HashMap<>();
            day.put("day", days[i]);
            day.put("revenue", revenues[i]);
            dailyRevenue.add(day);
        }
        analytics.put("dailyRevenue", dailyRevenue);
        
        return analytics;
    }
}