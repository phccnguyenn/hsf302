package javafx.fe_movie_ticket.controller;


import javafx.fe_movie_ticket.entity.Movie;
import javafx.fe_movie_ticket.entity.Showtime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MovieController {

    @GetMapping("/movies")
    public String showAllMovies(Model model) {
        // Create a large master list of mock movies
        List<Movie> allMovies = List.of(
                new Movie(1L, "Dune: Part Two", "Sci-Fi", 166, "/images/movie_posters/dune2.jpg", "NOW_SHOWING"),
                new Movie(2L, "Oppenheimer", "Drama", 180, "/images/movie_posters/oppenheimer.jpg", "NOW_SHOWING"),
                new Movie(3L, "The Super Mario Bros. Movie", "Animation", 92, "/images/movie_posters/mario.jpg", "NOW_SHOWING"),
                new Movie(4L, "Spider-Man: Across the Spider-Verse", "Animation", 140, "/images/movie_posters/spiderman.jpg", "NOW_SHOWING"),
                new Movie(5L, "The Creator", "Sci-Fi", 133, "/images/movie_posters/the_creator.jpg", "NOW_SHOWING"),
                new Movie(6L, "Past Lives", "Drama", 105, "/images/movie_posters/past_lives.jpg", "NOW_SHOWING"),
                new Movie(7L, "John Wick: Chapter 4", "Action", 169, "/images/movie_posters/john_wick4.jpg", "NOW_SHOWING"),
                new Movie(8L, "Barbie", "Comedy", 114, "/images/movie_posters/barbie.jpg", "NOW_SHOWING"),
                new Movie(9L, "Joker: Folie à Deux", "Thriller", 150, "/images/movie_posters/joker2.jpg", "COMING_SOON"),
                new Movie(10L, "Gladiator 2", "Action", 160, "/images/movie_posters/gladiator2.jpg", "COMING_SOON"),
                new Movie(11L, "Inside Out 2", "Animation", 100, "/images/movie_posters/inside_out2.jpg", "COMING_SOON")
        );

        // Filter the list into two separate lists for the tabs
        List<Movie> nowShowing = allMovies.stream()
                .filter(movie -> "NOW_SHOWING".equals(movie.getStatus()))
                .toList();

        List<Movie> comingSoon = allMovies.stream()
                .filter(movie -> "COMING_SOON".equals(movie.getStatus()))
                .toList();

        // Add both lists to the model so the template can access them
        model.addAttribute("nowShowingMovies", nowShowing);
        model.addAttribute("comingSoonMovies", comingSoon);

        return "movies";
    }

    @GetMapping("/movies/{id}")
    public String showMovieDetails(@PathVariable("id") Long movieId, Model model) {
        // In a real app, you'd fetch this movie from the database using the movieId.
        // For now, we create a detailed mock movie object.
        Movie mockMovie = new Movie(
                1L, "Dune: Part Two", "Sci-Fi", 166, "/images/movie_posters/dune2.jpg", "NOW_SHOWING",
                "PG-13",
                "Paul Atreides unites with Chani and the Fremen while on a warpath of revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the known universe, he endeavors to prevent a terrible future only he can foresee.",
                "https://www.youtube.com/embed/U2Qp5pL3ovA" // YouTube embed link
        );

        // Create a mock list of available showtimes for this movie
        model.addAttribute("movie", mockMovie);
        // model.addAttribute("showtimes", mockShowtimes); // DELETE THIS

        return "movie-details";
    }
}