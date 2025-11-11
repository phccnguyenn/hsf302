package javafx.fe_movie_ticket.controller;

import javafx.fe_movie_ticket.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookingController {

//    @GetMapping("/booking/select-seats/{movieId}/{showtimeId}")
//    public String showSeatSelectionPage(
//            @PathVariable("movieId") Long movieId,
//            @PathVariable("showtimeId") Long showtimeId,
//            Model model) {
//
//        // Mock movie and showtime data
//        Movie mockMovie = new Movie(movieId, "Dune: Part Two", "Sci-Fi", 166,
//            "https://via.placeholder.com/300x450/0066cc/white?text=Dune+Part+Two",
//            "Now Showing", "PG-13", "A mythic and emotionally charged hero's journey",
//            "https://www.youtube.com/embed/sample");
//
//        model.addAttribute("movie", mockMovie);
//        model.addAttribute("movieId", movieId);
//        model.addAttribute("showtimeId", showtimeId);
//
//        return "seat-selection";
//    }
//
//    // THIS IS THE CORRECTED METHOD
//    @GetMapping("/booking/{movieId}/showtimes")
//    public String showShowtimesByMovie(@PathVariable("movieId") Long movieId, Model model) {
//
//        // 1. Mock the specific movie being booked
//        Movie mockMovie = new Movie(movieId, "Dune: Part Two", "Sci-Fi", 166, "/images/movie_posters/dune2.jpg", "NOW_SHOWING", "PG-13", "...", "...");
//        model.addAttribute("movie", mockMovie);
//
//        // 2. Create mock auditoriums for the selected cinema
//        Auditorium standardScreen =new Auditorium(501L, "Screen 1", "2D Phụ Đề Anh");
//        // (If you had other auditoriums like Gold Class, you would define them here too)
//
//        // 3. Create showtimes linked to those specific auditoriums
//        List<Showtime> showtimesForCinema1 = List.of(
//                new Showtime(101L, "13:40", standardScreen), // Use the Auditorium object
//                new Showtime(102L, "18:30", standardScreen)  // Use the Auditorium object
//        );
//        List<Showtime> showtimesForCinema2 = List.of(
//                new Showtime(103L, "15:20", standardScreen), // Assuming a standard screen here too
//                new Showtime(104L, "19:45", standardScreen)
//        );
//
//        // 4. Group the showtimes by cinema (as the page layout requires)
//        Map<Cinema, List<Showtime>> showtimesByCinema = new LinkedHashMap<>();
//
//        Cinema cinema1 = new Cinema(201L, "CGV Vincom Center Bà Triệu", "Hà Nội");
//        showtimesByCinema.put(cinema1, showtimesForCinema1);
//
//        Cinema cinema2 = new Cinema(202L, "CGV Vincom Bắc Từ Liêm", "Hà Nội");
//        showtimesByCinema.put(cinema2, showtimesForCinema2);
//
//        // 5. Add the final data structure to the model
//        model.addAttribute("showtimesByCinema", showtimesByCinema);
//
//        // 6. Return the template name
//        return "showtimes-by-movie";
//    }
//
//    @GetMapping("/booking/refreshments")
//    public String showRefreshmentSelection(Model model) {
//        // Mock refreshment categories
//        Map<String, List<Refreshment>> refreshmentsByCategory = new LinkedHashMap<>();
//
//        // Combos
//        refreshmentsByCategory.put("COMBO", List.of(
//            new Refreshment(1L, "CGV Combo 1", "1 bắp rang bơ vừa + 1 nước ngọt", 149000, "COMBO",
//                "https://images.unsplash.com/photo-1578662996442-48f60103fc96?w=400&h=300&fit=crop"),
//            new Refreshment(2L, "CGV Combo 2", "1 bắp rang bơ lớn + 2 nước ngọt", 219000, "COMBO",
//                "https://images.unsplash.com/photo-1578662996442-48f60103fc96?w=400&h=300&fit=crop")
//        ));
//
//        // Popcorn - 3 vị, 2 size
//        refreshmentsByCategory.put("POPCORN", List.of(
//            new Refreshment(3L, "Bắp rang bơ vừa", "Bắp ngô rang bơ thơm ngon", 79000, "POPCORN",
//                "https://images.unsplash.com/photo-1585647347483-22b66260dfff?w=400&h=300&fit=crop"),
//            new Refreshment(4L, "Bắp rang bơ lớn", "Bắp ngô rang bơ thơm ngon", 99000, "POPCORN",
//                "https://images.unsplash.com/photo-1585647347483-22b66260dfff?w=400&h=300&fit=crop"),
//            new Refreshment(5L, "Bắp caramel vừa", "Bắp phủ caramel ngọt ngào", 89000, "POPCORN",
//                "https://images.unsplash.com/photo-1585647347483-22b66260dfff?w=400&h=300&fit=crop"),
//            new Refreshment(6L, "Bắp caramel lớn", "Bắp phủ caramel ngọt ngào", 109000, "POPCORN",
//                "https://images.unsplash.com/photo-1585647347483-22b66260dfff?w=400&h=300&fit=crop"),
//            new Refreshment(7L, "Bắp phô mai vừa", "Bắp phủ phô mai đậm đà", 89000, "POPCORN",
//                "https://images.unsplash.com/photo-1585647347483-22b66260dfff?w=400&h=300&fit=crop"),
//            new Refreshment(8L, "Bắp phô mai lớn", "Bắp phủ phô mai đậm đà", 109000, "POPCORN",
//                "https://images.unsplash.com/photo-1585647347483-22b66260dfff?w=400&h=300&fit=crop")
//        ));
//
//        // Drinks - 2 loại nước, 2 size
//        refreshmentsByCategory.put("DRINK", List.of(
//            new Refreshment(9L, "Pepsi vừa", "Nước ngọt có gas", 49000, "DRINK",
//                "https://images.unsplash.com/photo-1554866585-cd94860890b7?w=400&h=300&fit=crop"),
//            new Refreshment(10L, "Pepsi lớn", "Nước ngọt có gas", 69000, "DRINK",
//                "https://images.unsplash.com/photo-1554866585-cd94860890b7?w=400&h=300&fit=crop"),
//            new Refreshment(11L, "7UP vừa", "Nước ngọt chanh", 49000, "DRINK",
//                "https://images.unsplash.com/photo-1554866585-cd94860890b7?w=400&h=300&fit=crop"),
//            new Refreshment(12L, "7UP lớn", "Nước ngọt chanh", 69000, "DRINK",
//                "https://images.unsplash.com/photo-1554866585-cd94860890b7?w=400&h=300&fit=crop")
//        ));
//
//        // Snacks
//        refreshmentsByCategory.put("SNACK", List.of(
//            new Refreshment(13L, "Kẹo dẻo", "Kẹo dẻo nhiều vị", 45000, "SNACK",
//                "https://images.unsplash.com/photo-1582058091505-f87a2e55a40f?w=400&h=300&fit=crop"),
//            new Refreshment(14L, "Chocolate", "Socola đầy màu sắc", 55000, "SNACK",
//                "https://images.unsplash.com/photo-1582058091505-f87a2e55a40f?w=400&h=300&fit=crop")
//        ));
//
//        model.addAttribute("refreshmentsByCategory", refreshmentsByCategory);
//        return "refreshment-selection";
//    }
    
}