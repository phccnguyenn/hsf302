package javafx.fe_movie_ticket.controller;

import javafx.fe_movie_ticket.entity.Cinema;
import javafx.fe_movie_ticket.entity.Movie;
import javafx.fe_movie_ticket.entity.Showtime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

        // In a real app, you would use these IDs to fetch all the necessary
        // information: movie title, showtime, auditorium layout, and which seats are already booked.

        // For now, we just pass the IDs to the page to confirm they were received.
        model.addAttribute("movieId", movieId);
        model.addAttribute("showtimeId", showtimeId);

        return "seat-selection";
    }
    @GetMapping("/booking/{movieId}/showtimes")
    public String showShowtimesByMovie(@PathVariable("movieId") Long movieId, Model model) {
        // 1. Mock the movie being booked
        Movie mockMovie = new Movie(movieId, "Dune: Part Two", "Sci-Fi", 166, "...", "...", "...", "...", "...");
        model.addAttribute("movie", mockMovie);

        // 2. Mock the data structure: A list of showtimes grouped by cinema
        Map<Cinema, List<Showtime>> showtimesByCinema = new LinkedHashMap<>();

        // Mock Cinema 1 and its showtimes
        Cinema cinema1 = new Cinema(201L, "CGV Vincom Center Bà Triệu", "Hà Nội");
        List<Showtime> showtimes1 = List.of(
                new Showtime(101L, "13:40", "2D"),
                new Showtime(102L, "18:30", "2D")
        );
        showtimesByCinema.put(cinema1, showtimes1);

        // Mock Cinema 2 and its showtimes
        Cinema cinema2 = new Cinema(202L, "CGV Vincom Bắc Từ Liêm", "Hà Nội");
        List<Showtime> showtimes2 = List.of(
                new Showtime(103L, "15:20", "2D"),
                new Showtime(104L, "19:45", "2D")
        );
        showtimesByCinema.put(cinema2, showtimes2);

        // 3. Add the map to the model
        model.addAttribute("showtimesByCinema", showtimesByCinema);

        // 4. Return the new template name
        return "showtimes-by-movie";
    }

}