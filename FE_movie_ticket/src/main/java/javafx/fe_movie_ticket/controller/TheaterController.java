package javafx.fe_movie_ticket.controller;

import javafx.fe_movie_ticket.entity.Auditorium;
import javafx.fe_movie_ticket.entity.Cinema;
import javafx.fe_movie_ticket.entity.Movie;
import javafx.fe_movie_ticket.entity.Showtime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class TheaterController {

//    @GetMapping("/theaters")
//    public String showTheatersPage(Model model) {
//        // --- MOCK DATA ---
//
//        // 1. A list of all available cities
//        List<String> cities = List.of("Hà Nội", "TP. Hồ Chí Minh");
//        model.addAttribute("cities", cities);
//
//        // 2. A map of all cinemas, grouped by their city name
//        Map<String, List<Cinema>> cinemasByCity = new LinkedHashMap<>();
//        cinemasByCity.put("Hà Nội", List.of(
//                new Cinema(201L, "CGV Vincom Center Bà Triệu", "Hà Nội"),
//                new Cinema(202L, "CGV Vincom Bắc Từ Liêm", "Hà Nội")
//        ));
//        cinemasByCity.put("TP. Hồ Chí Minh", List.of(
//                new Cinema(301L, "CGV Crescent Mall", "TP. HCM")
//        ));
//        model.addAttribute("cinemasByCity", cinemasByCity);
//
//        // 3. The detailed showtime schedule for a *default* selected cinema
//        Auditorium standardScreen = new Auditorium(501L, "Screen 1", "2D Phụ Đề Anh");
//        Auditorium goldClass = new Auditorium(502L, "Gold Class 1", "2D Phụ Đề Anh | Rạp GOLD CLASS");
//
//        List<Showtime> showtimes = List.of(
//                new Showtime(101L, "10:10", standardScreen), new Showtime(102L, "11:50", standardScreen),
//                new Showtime(105L, "11:00", goldClass), new Showtime(106L, "13:30", goldClass)
//        );
//
//        Map<String, List<Showtime>> groupedByFormat = showtimes.stream()
//                .collect(Collectors.groupingBy(showtime -> showtime.getAuditorium().getFormat()));
//
//        Map<Movie, Map<String, List<Showtime>>> showtimesByMovie = new LinkedHashMap<>();
//        Movie movie1 = new Movie(1L, "CỤC VÀNG CỦA NGOẠI", null, 0, "/images/movie_posters/cuc_vang.jpg", null, "T13", null, null);
//        showtimesByMovie.put(movie1, groupedByFormat);
//
//        model.addAttribute("showtimesByMovie", showtimesByMovie);
//
//        return "theaters";
//    }
}