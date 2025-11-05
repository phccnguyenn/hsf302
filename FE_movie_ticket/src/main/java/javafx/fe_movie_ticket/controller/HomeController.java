package javafx.fe_movie_ticket.controller;

import javafx.fe_movie_ticket.entity.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomePage(Model model) {
        // Mock data for "Now Showing"
        List<Movie> nowShowing = List.of(
                new Movie(1L, "Dune: Part Two", "Sci-Fi", 166, "/images/movie_posters/dune2.jpg", "NOW_SHOWING"),
                new Movie(2L, "Oppenheimer", "Drama", 180, "/images/movie_posters/oppenheimer.jpg", "NOW_SHOWING"),
                new Movie(3L, "The Super Mario Bros. Movie", "Animation", 92, "/images/movie_posters/mario.jpg", "NOW_SHOWING"),
                new Movie(4L, "Spider-Man: Across the Spider-Verse", "Animation", 140, "/images/movie_posters/spiderman.jpg", "NOW_SHOWING")
        );

        // Mock data for "Coming Soon" (we can add a tab for this later)
        // List<Movie> comingSoon = ...

        model.addAttribute("nowShowingMovies", nowShowing);
        return "index";
    }
}