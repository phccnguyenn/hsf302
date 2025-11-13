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
        // Mock data for "Now Showing" - using setter methods
        Movie movie1 = new Movie();
        movie1.setMovieId(1L);
        movie1.setTitle("Dune: Part Two");
        movie1.setDuration(166);
        movie1.setMovieUrl("/images/movie_posters/dune2.jpg");
        movie1.setActive(true);

        Movie movie2 = new Movie();
        movie2.setMovieId(2L);
        movie2.setTitle("Oppenheimer");
        movie2.setDuration(180);
        movie2.setMovieUrl("/images/movie_posters/oppenheimer.jpg");
        movie2.setActive(true);

        Movie movie3 = new Movie();
        movie3.setMovieId(3L);
        movie3.setTitle("The Super Mario Bros. Movie");
        movie3.setDuration(92);
        movie3.setMovieUrl("/images/movie_posters/mario.jpg");
        movie3.setActive(true);

        Movie movie4 = new Movie();
        movie4.setMovieId(4L);
        movie4.setTitle("Spider-Man: Across the Spider-Verse");
        movie4.setDuration(140);
        movie4.setMovieUrl("/images/movie_posters/spiderman.jpg");
        movie4.setActive(true);

        List<Movie> nowShowing = List.of(movie1, movie2, movie3, movie4);

        // Mock data for "Coming Soon" (we can add a tab for this later)
        // List<Movie> comingSoon = ...

        model.addAttribute("nowShowingMovies", nowShowing);
        return "index";
    }
}