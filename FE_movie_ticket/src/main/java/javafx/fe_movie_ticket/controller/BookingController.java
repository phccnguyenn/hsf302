package javafx.fe_movie_ticket.controller;

import javafx.fe_movie_ticket.entity.*;
import javafx.fe_movie_ticket.entity.enumeration.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookingController {

    @GetMapping("/booking/select-seats/{movieId}/{showtimeId}")
    public String showSeatSelectionPage(
            @PathVariable("movieId") Long movieId,
            @PathVariable("showtimeId") Long showtimeId,
            Model model) {

        // Create mock movie data using proper constructor
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(movieId);
        mockMovie.setTitle("Dune: Part Two");
        mockMovie.setDuration(166);
        mockMovie.setMovieUrl("https://image.tmdb.org/t/p/w500/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg");
        mockMovie.setDescription("A mythic and emotionally charged hero's journey");

        model.addAttribute("movie", mockMovie);
        model.addAttribute("movieId", movieId);
        model.addAttribute("showtimeId", showtimeId);

        return "seat-selection";
    }

    @GetMapping("/booking/{movieId}/showtimes")
    public String showShowtimesByMovie(@PathVariable("movieId") Long movieId, Model model) {

        // 1. Mock the specific movie being booked based on movieId
        Movie mockMovie = new Movie();
        mockMovie.setMovieId(movieId);
        
        // Set different movie details based on movieId
        switch (movieId.intValue()) {
            case 1:
                mockMovie.setTitle("Dune: Part Two");
                mockMovie.setMovieUrl("https://image.tmdb.org/t/p/w500/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg");
                break;
            case 2:
                mockMovie.setTitle("Oppenheimer");
                mockMovie.setMovieUrl("https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg");
                break;
            case 3:
                mockMovie.setTitle("Spider-Man: Across the Spider-Verse");
                mockMovie.setMovieUrl("https://image.tmdb.org/t/p/w500/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg");
                break;
            case 4:
                mockMovie.setTitle("Avengers: Endgame");
                mockMovie.setMovieUrl("https://image.tmdb.org/t/p/w500/or06FN3Dka5tukK1e9sl16pB3iy.jpg");
                break;
            default:
                mockMovie.setTitle("Featured Movie");
                mockMovie.setMovieUrl("https://image.tmdb.org/t/p/w500/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg");
        }
        mockMovie.setDuration(166);
        mockMovie.setDescription("Experience this amazing movie in our premium theaters");
        
        model.addAttribute("movie", mockMovie);

        // 2. Create mock showtimes data structure expected by template
        Map<String, Map<String, List<Map<String, Object>>>> showtimeData = new LinkedHashMap<>();
        
        // Create showtime entries
        List<Map<String, Object>> showtimes1 = List.of(
            Map.of("time", "13:40", "showtimeId", 101L),
            Map.of("time", "16:20", "showtimeId", 102L),
            Map.of("time", "19:00", "showtimeId", 103L)
        );
        
        List<Map<String, Object>> showtimes2 = List.of(
            Map.of("time", "14:30", "showtimeId", 104L),
            Map.of("time", "17:15", "showtimeId", 105L),
            Map.of("time", "20:00", "showtimeId", 106L)
        );
        
        // Group by cinema and format
        Map<String, List<Map<String, Object>>> formats1 = new LinkedHashMap<>();
        formats1.put("2D Phụ Đề", showtimes1);
        
        Map<String, List<Map<String, Object>>> formats2 = new LinkedHashMap<>(); 
        formats2.put("2D Phụ Đề", showtimes2);
        
        showtimeData.put("CGV Vincom Center Bà Triệu", formats1);
        showtimeData.put("CGV Vincom Bắc Từ Liêm", formats2);

        model.addAttribute("showtimeData", showtimeData);

        return "showtimes-by-movie";
    }

    @GetMapping("/booking/refreshments")
    public String showRefreshmentSelection(Model model) {
        // Mock refreshment categories
        Map<String, List<Refreshment>> refreshmentsByCategory = new LinkedHashMap<>();

        // Combos
        List<Refreshment> combos = new ArrayList<>();
        combos.add(new Refreshment(1L, "CGV Combo 1", "1 bắp rang bơ vừa + 1 nước ngọt", 149000.0, Category.COMBO, "https://images.unsplash.com/photo-1578662996442-48f60103fc96?w=400&h=300&fit=crop"));
        combos.add(new Refreshment(2L, "CGV Combo 2", "1 bắp rang bơ lớn + 2 nước ngọt", 219000.0, Category.COMBO, "https://images.unsplash.com/photo-1578662996442-48f60103fc96?w=400&h=300&fit=crop"));
        refreshmentsByCategory.put("COMBO", combos);

        // Popcorn (using SNACK category since POPCORN doesn't exist in enum)
        List<Refreshment> popcorn = new ArrayList<>();
        popcorn.add(new Refreshment(3L, "Bắp rang bơ vừa", "Bắp ngô rang bơ thơm ngon", 79000.0, Category.SNACK, "https://images.unsplash.com/photo-1585647347483-22b66260dfff?w=400&h=300&fit=crop"));
        popcorn.add(new Refreshment(4L, "Bắp rang bơ lớn", "Bắp ngô rang bơ thơm ngon", 99000.0, Category.SNACK, "https://images.unsplash.com/photo-1585647347483-22b66260dfff?w=400&h=300&fit=crop"));
        refreshmentsByCategory.put("POPCORN", popcorn);

        // Drinks
        List<Refreshment> drinks = new ArrayList<>();
        drinks.add(new Refreshment(9L, "Pepsi vừa", "Nước ngọt có gas", 49000.0, Category.DRINK, "https://images.unsplash.com/photo-1554866585-cd94860890b7?w=400&h=300&fit=crop"));
        drinks.add(new Refreshment(10L, "Pepsi lớn", "Nước ngọt có gas", 69000.0, Category.DRINK, "https://images.unsplash.com/photo-1554866585-cd94860890b7?w=400&h=300&fit=crop"));
        refreshmentsByCategory.put("DRINK", drinks);

        // Snacks
        List<Refreshment> snacks = new ArrayList<>();
        snacks.add(new Refreshment(13L, "Kẹo dẻo", "Kẹo dẻo nhiều vị", 45000.0, Category.SNACK, "https://images.unsplash.com/photo-1582058091505-f87a2e55a40f?w=400&h=300&fit=crop"));
        snacks.add(new Refreshment(14L, "Chocolate", "Socola đầy màu sắc", 55000.0, Category.SNACK, "https://images.unsplash.com/photo-1582058091505-f87a2e55a40f?w=400&h=300&fit=crop"));
        refreshmentsByCategory.put("SNACK", snacks);

        model.addAttribute("refreshmentsByCategory", refreshmentsByCategory);
        return "refreshment-selection";
    }
    
}