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
        // Mock data for "Now Showing" - using real poster URLs from TMDb
        // movieId khớp với MovieController để trang detail hiển thị đúng
        Movie movie1 = new Movie();
        movie1.setMovieId(1L);
        movie1.setTitle("Dune: Part Two");
        movie1.setDuration(166);
        movie1.setMovieUrl("https://image.tmdb.org/t/p/w500/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg");
        movie1.setBackdropUrl("https://image.tmdb.org/t/p/w1280/AnpWF2PWMn62RuaaKllaJkh6wSc.jpg");
        movie1.setDescription("Follow the mythic journey of Paul Atreides as he unites with Chani and the Fremen while on a path of revenge against the conspirators who destroyed his family.");
        movie1.setActive(true);

        Movie movie2 = new Movie();
        movie2.setMovieId(2L);
        movie2.setTitle("Oppenheimer");
        movie2.setDuration(180);
        movie2.setMovieUrl("https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg");
        movie2.setBackdropUrl("https://image.tmdb.org/t/p/w1280/fm6KqXpk3M2HVveHwCrBSSBaO0V.jpg");
        movie2.setDescription("The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb during World War II.");
        movie2.setActive(true);

        Movie movie3 = new Movie();
        movie3.setMovieId(3L);
        movie3.setTitle("Spider-Man: Across the Spider-Verse");
        movie3.setDuration(140);
        movie3.setMovieUrl("https://image.tmdb.org/t/p/w500/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg");
        movie3.setBackdropUrl("https://image.tmdb.org/t/p/w1280/4HodYYKEIsGOdinkGi2Ucz6X9i0.jpg");
        movie3.setDescription("Miles Morales catapults across the Multiverse, where he encounters a team of Spider-People charged with protecting existence itself.");
        movie3.setActive(true);

        Movie movie4 = new Movie();
        movie4.setMovieId(4L);
        movie4.setTitle("The Super Mario Bros. Movie");
        movie4.setDuration(92);
        movie4.setMovieUrl("https://image.tmdb.org/t/p/w500/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg");
        movie4.setBackdropUrl("https://image.tmdb.org/t/p/w1280/9n2tJBplPbgR2ca05hS5CKXwP2c.jpg");
        movie4.setDescription("A plumber named Mario travels through an underground labyrinth with his brother Luigi, trying to save a captured princess.");
        movie4.setActive(true);

        // Thêm nhiều phim mới - cập nhật movieId để khớp với MovieController
        Movie movie5 = new Movie();
        movie5.setMovieId(7L); // The Batman
        movie5.setTitle("The Batman");
        movie5.setDuration(176);
        movie5.setMovieUrl("https://image.tmdb.org/t/p/w500/74xTEgt7R36Fpooo50r9T25onhq.jpg");
        movie5.setBackdropUrl("https://image.tmdb.org/t/p/w1280/b0PlSFdDwbyK0cf5RxwDpaOJQvQ.jpg");
        movie5.setDescription("In his second year of fighting crime, Batman uncovers corruption in Gotham City that connects to his own family while facing a serial killer known as the Riddler.");
        movie5.setActive(true);

        Movie movie6 = new Movie();
        movie6.setMovieId(8L); // Top Gun: Maverick
        movie6.setTitle("Top Gun: Maverick");
        movie6.setDuration(131);
        movie6.setMovieUrl("https://image.tmdb.org/t/p/w500/62HCnUTziyWcpDaBO2i1DX17ljH.jpg");
        movie6.setBackdropUrl("https://image.tmdb.org/t/p/w1280/odM92ap21JJaus0O9ODG0qd0wnS.jpg");
        movie6.setDescription("After more than thirty years of service as one of the Navy's top aviators, Pete Mitchell is where he belongs, pushing the envelope as a courageous test pilot.");
        movie6.setActive(true);

        Movie movie7 = new Movie();
        movie7.setMovieId(9L); // Avengers: Endgame
        movie7.setTitle("Avengers: Endgame");
        movie7.setDuration(181);
        movie7.setMovieUrl("https://image.tmdb.org/t/p/w500/or06FN3Dka5tukK1e9sl16pB3iy.jpg");
        movie7.setBackdropUrl("https://image.tmdb.org/t/p/w1280/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg");
        movie7.setDescription("After the devastating events of Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more.");
        movie7.setActive(true);

        Movie movie8 = new Movie();
        movie8.setMovieId(6L); // Black Panther: Wakanda Forever
        movie8.setTitle("Black Panther: Wakanda Forever");
        movie8.setDuration(161);
        movie8.setMovieUrl("https://image.tmdb.org/t/p/w500/sv1xJUazXeYqALzczSZ3O6nkH75.jpg");
        movie8.setBackdropUrl("https://image.tmdb.org/t/p/w1280/yYrvN5WFeGYjJnRzhY0QXuo4Isw.jpg");
        movie8.setDescription("Queen Ramonda, Shuri, M'Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T'Challa's death.");
        movie8.setActive(true);

        List<Movie> nowShowing = List.of(movie1, movie2, movie3, movie4, movie5, movie6, movie7, movie8);

        // Mock data for "Coming Soon" movies - sử dụng movieId lớn hơn 10
        Movie comingSoon1 = new Movie();
        comingSoon1.setMovieId(5L); // Joker: Folie à Deux (coming soon in MovieController)
        comingSoon1.setTitle("Joker: Folie à Deux");
        comingSoon1.setDuration(150);
        comingSoon1.setMovieUrl("https://image.tmdb.org/t/p/w500/if8QiqCI7WAGImKcJCfBp6VTyKA.jpg");
        comingSoon1.setBackdropUrl("https://image.tmdb.org/t/p/w1280/iKSbqAamDzgUgOIFoMCmQwL0Yyp.jpg");
        comingSoon1.setDescription("Arthur Fleck is institutionalized at Arkham awaiting trial for his crimes as Joker. While struggling with his dual identity, Arthur not only stumbles upon true love, but also finds the music that's always been inside him.");
        comingSoon1.setActive(false);

        Movie comingSoon2 = new Movie();
        comingSoon2.setMovieId(10L);
        comingSoon2.setTitle("Fast X");
        comingSoon2.setDuration(141);
        comingSoon2.setMovieUrl("https://image.tmdb.org/t/p/w500/fiVW06jE7z9YnO4trhaMEdclSiC.jpg");
        comingSoon2.setBackdropUrl("https://image.tmdb.org/t/p/w1280/yOm993lsJyPmBodlYjgpPwHeI9C.jpg");
        comingSoon2.setDescription("Over many missions and against impossible odds, Dom Toretto and his family have outsmarted, out-nerved and outdriven every foe in their path.");
        comingSoon2.setActive(false);

        Movie comingSoon3 = new Movie();
        comingSoon3.setMovieId(11L);
        comingSoon3.setTitle("John Wick: Chapter 4");
        comingSoon3.setDuration(169);
        comingSoon3.setMovieUrl("https://image.tmdb.org/t/p/w500/vZloFAK7NmvMGKE7VkF5UHaz0I.jpg");
        comingSoon3.setBackdropUrl("https://image.tmdb.org/t/p/w1280/fqv8v6AycXKsivp1T5yKtLbGXce.jpg");
        comingSoon3.setDescription("John Wick uncovers a path to defeating The High Table. But before he can earn his freedom, Wick must face off against a new enemy.");
        comingSoon3.setActive(false);

        Movie comingSoon4 = new Movie();
        comingSoon4.setMovieId(12L);
        comingSoon4.setTitle("Transformers: Rise of the Beasts");
        comingSoon4.setDuration(127);
        comingSoon4.setMovieUrl("https://image.tmdb.org/t/p/w500/gPbM0MK8CP8A174rmUwGsADNYKD.jpg");
        comingSoon4.setBackdropUrl("https://image.tmdb.org/t/p/w1280/bz66a19bR6BKsbY8gSZCM4etJiK.jpg");
        comingSoon4.setDescription("A '90s globetrotting adventure that introduces the Maximals, Predacons, and Terrorcons to the existing battle on earth between Autobots and Decepticons.");
        comingSoon4.setActive(false);

        List<Movie> comingSoon = List.of(comingSoon1, comingSoon2, comingSoon3, comingSoon4);

        // Hero slides data (sử dụng 4 phim nổi bật nhất với movieId đã sửa)
        List<Movie> heroSlides = List.of(movie1, movie2, movie7, movie3); // Dune, Oppenheimer, Avengers, Spider-Man

        model.addAttribute("nowShowingMovies", nowShowing);
        model.addAttribute("comingSoonMovies", comingSoon);
        model.addAttribute("heroSlides", heroSlides);
        return "index";
    }
}