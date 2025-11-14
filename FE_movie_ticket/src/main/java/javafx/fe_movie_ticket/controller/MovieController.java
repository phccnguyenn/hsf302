package javafx.fe_movie_ticket.controller;


import javafx.fe_movie_ticket.entity.Genres;
import javafx.fe_movie_ticket.entity.Movie;
import javafx.fe_movie_ticket.entity.dto.MoviePostDto;
import javafx.fe_movie_ticket.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/genres")
    public String allGenres() {
        // List<Genres> genres = movieService.getAllGenres();
        return "";
    }

    @GetMapping("/movies")
    public String allMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        
        // Nếu database rỗng, tạo mock data để test
        if (movies.isEmpty()) {
            movies = createMockMoviesWithGenres();
        }
        
        // Phân loại movies theo status
        List<Movie> nowShowing = movies.stream()
                .filter(Movie::isActive)
                .toList();
        
        List<Movie> comingSoon = movies.stream()
                .filter(movie -> !movie.isActive())
                .toList();
        
        model.addAttribute("nowShowingMovies", nowShowing);
        model.addAttribute("comingSoonMovies", comingSoon);
        return "movies";
    }
    
    
    private List<Movie> createMockMoviesWithGenres() {
        List<Movie> mockMovies = new java.util.ArrayList<>();
        java.time.OffsetDateTime now = java.time.OffsetDateTime.now();
        
        // Now Showing Movies
        Movie movie1 = new Movie();
        movie1.setMovieId(1L);
        movie1.setTitle("Dune: Part Two");
        movie1.setDuration(166);
        movie1.setMovieUrl("https://image.tmdb.org/t/p/w500/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg");
        movie1.setBackdropUrl("https://image.tmdb.org/t/p/original/xOMo8BRK7PfcJv9JCnx7s5hj0PX.jpg");
        movie1.setTrailerUrl("https://www.youtube.com/embed/Way9Dexny3w");
        movie1.setDescription("Paul Atreides unites with Chani and the Fremen while on a warpath of revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the known universe, he endeavors to prevent a terrible future only he can foresee.");
        movie1.setReleaseDate(now.minusDays(30));
        movie1.setRating(8.5);
        movie1.setLanguage("English");
        movie1.setDirector("Denis Villeneuve");
        movie1.setCast("Timothée Chalamet, Zendaya, Rebecca Ferguson");
        movie1.setAgeRating("PG-13");
        movie1.setActive(true);
        
        // Add genres for Dune
        List<Genres> duneGenres = new ArrayList<>();
        Genres sciFi = new Genres();
        sciFi.setGenresId(1L);
        sciFi.setGenresName("Sci-Fi");
        sciFi.setMovie(movie1);
        duneGenres.add(sciFi);
        
        Genres adventure = new Genres();
        adventure.setGenresId(2L);
        adventure.setGenresName("Adventure");
        adventure.setMovie(movie1);
        duneGenres.add(adventure);
        
        movie1.setGenresList(duneGenres);
        mockMovies.add(movie1);
        
        Movie movie2 = new Movie();
        movie2.setMovieId(2L);
        movie2.setTitle("Oppenheimer");
        movie2.setDuration(180);
        movie2.setMovieUrl("https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg");
        movie2.setBackdropUrl("https://image.tmdb.org/t/p/original/rLb2cwF3Pazuxaj0sRXQ037tGI1.jpg");
        movie2.setTrailerUrl("https://www.youtube.com/embed/uYPbbksJxIg");
        movie2.setDescription("The story of J. Robert Oppenheimer's role in the development of the atomic bomb during World War II. A gripping biographical drama that explores the moral complexities of scientific discovery and its devastating consequences.");
        movie2.setReleaseDate(now.minusDays(60));
        movie2.setRating(8.3);
        movie2.setLanguage("English");
        movie2.setDirector("Christopher Nolan");
        movie2.setCast("Cillian Murphy, Emily Blunt, Robert Downey Jr.");
        movie2.setAgeRating("R");
        movie2.setActive(true);
        
        // Add genres for Oppenheimer
        List<Genres> oppGenres = new ArrayList<>();
        Genres drama = new Genres();
        drama.setGenresId(3L);
        drama.setGenresName("Drama");
        drama.setMovie(movie2);
        oppGenres.add(drama);
        
        Genres history = new Genres();
        history.setGenresId(4L);
        history.setGenresName("History");
        history.setMovie(movie2);
        oppGenres.add(history);
        
        movie2.setGenresList(oppGenres);
        mockMovies.add(movie2);
        
        Movie movie3 = new Movie();
        movie3.setMovieId(3L);
        movie3.setTitle("Spider-Man: Across the Spider-Verse");
        movie3.setDuration(140);
        movie3.setMovieUrl("https://image.tmdb.org/t/p/w500/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg");
        movie3.setBackdropUrl("https://image.tmdb.org/t/p/original/nGxUxi3PfXDRm7Vg95VBNgNM8yc.jpg");
        movie3.setTrailerUrl("https://www.youtube.com/embed/cqGjhVJWtEg");
        movie3.setDescription("After reuniting with Gwen Stacy, Brooklyn's full-time, friendly neighborhood Spider-Man is catapulted across the Multiverse, where he encounters the Spider Society, a team of Spider-People charged with protecting the Multiverse's very existence.");
        movie3.setReleaseDate(now.minusDays(45));
        movie3.setRating(8.7);
        movie3.setLanguage("English");
        movie3.setDirector("Joaquim Dos Santos, Kemp Powers, Justin K. Thompson");
        movie3.setCast("Shameik Moore, Hailee Steinfeld, Brian Tyree Henry");
        movie3.setAgeRating("PG");
        movie3.setActive(true);
        
        // Add genres for Spider-Man
        List<Genres> spiderGenres = new ArrayList<>();
        Genres animation = new Genres();
        animation.setGenresId(5L);
        animation.setGenresName("Animation");
        animation.setMovie(movie3);
        spiderGenres.add(animation);
        
        Genres action = new Genres();
        action.setGenresId(6L);
        action.setGenresName("Action");
        action.setMovie(movie3);
        spiderGenres.add(action);
        
        movie3.setGenresList(spiderGenres);
        mockMovies.add(movie3);
        
        Movie movie4 = new Movie();
        movie4.setMovieId(4L);
        movie4.setTitle("The Super Mario Bros. Movie");
        movie4.setDuration(92);
        movie4.setMovieUrl("https://image.tmdb.org/t/p/w500/qNBAXBIQlnOThrVvA6mTahRTvSr.jpg");
        movie4.setBackdropUrl("https://image.tmdb.org/t/p/original/9n2tJBplPbgR2ca05hS5CKXwP2c.jpg");
        movie4.setTrailerUrl("https://www.youtube.com/embed/TnGl01FkMMo");
        movie4.setDescription("While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world. But when the brothers are separated, Mario embarks on an epic quest to find Luigi.");
        movie4.setReleaseDate(now.minusDays(90));
        movie4.setRating(7.0);
        movie4.setLanguage("English");
        movie4.setDirector("Aaron Horvath, Michael Jelenic");
        movie4.setCast("Chris Pratt, Anya Taylor-Joy, Charlie Day");
        movie4.setAgeRating("PG");
        movie4.setActive(true);
        
        // Add genres for Mario
        List<Genres> marioGenres = new ArrayList<>();
        Genres animationMario = new Genres();
        animationMario.setGenresId(7L);
        animationMario.setGenresName("Animation");
        animationMario.setMovie(movie4);
        marioGenres.add(animationMario);
        
        Genres family = new Genres();
        family.setGenresId(8L);
        family.setGenresName("Family");
        family.setMovie(movie4);
        marioGenres.add(family);
        
        movie4.setGenresList(marioGenres);
        mockMovies.add(movie4);
        
        // Coming Soon Movies  
        Movie movie5 = new Movie();
        movie5.setMovieId(5L);
        movie5.setTitle("Joker: Folie à Deux");
        movie5.setDuration(150);
        movie5.setMovieUrl("https://image.tmdb.org/t/p/w500/if8QiqCI7WAGImKcJCfBp6VTyKA.jpg");
        movie5.setBackdropUrl("https://image.tmdb.org/t/p/original/iKSbqAamDzgUgOIFoMCmQwL0Yyp.jpg");
        movie5.setDescription("Arthur Fleck is institutionalized at Arkham awaiting trial for his crimes as Joker. While struggling with his dual identity, Arthur not only stumbles upon true love, but also finds the music that's always been inside him.");
        movie5.setReleaseDate(now.plusDays(30));
        movie5.setActive(false);
        
        // Add genres for Joker
        List<Genres> jokerGenres = new ArrayList<>();
        Genres dramJoker = new Genres();
        dramJoker.setGenresId(9L);
        dramJoker.setGenresName("Drama");
        dramJoker.setMovie(movie5);
        jokerGenres.add(dramJoker);
        
        Genres thriller = new Genres();
        thriller.setGenresId(10L);
        thriller.setGenresName("Thriller");
        thriller.setMovie(movie5);
        jokerGenres.add(thriller);
        
        movie5.setGenresList(jokerGenres);
        mockMovies.add(movie5);
        
        Movie movie6 = new Movie();
        movie6.setMovieId(6L);
        movie6.setTitle("Black Panther: Wakanda Forever");
        movie6.setDuration(161);
        movie6.setMovieUrl("https://image.tmdb.org/t/p/w500/sv1xJUazXeYqALzczSZ3O6nkH75.jpg");
        movie6.setBackdropUrl("https://image.tmdb.org/t/p/original/yYrvN5WFeGYjJnRzhY0QXuo4Isw.jpg");
        movie6.setDescription("Queen Ramonda, Shuri, M'Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T'Challa's death. As the Wakandans strive to embrace their next chapter, the heroes must band together with the help of War Dog Nakia and Everett Ross to forge a new path for the kingdom of Wakanda.");
        movie6.setReleaseDate(now.plusDays(15));
        movie6.setActive(false);
        
        // Add genres for Black Panther
        List<Genres> bpGenres = new ArrayList<>();
        Genres actionBP = new Genres();
        actionBP.setGenresId(11L);
        actionBP.setGenresName("Action");
        actionBP.setMovie(movie6);
        bpGenres.add(actionBP);
        
        Genres sciFiBP = new Genres();
        sciFiBP.setGenresId(12L);
        sciFiBP.setGenresName("Sci-Fi");
        sciFiBP.setMovie(movie6);
        bpGenres.add(sciFiBP);
        
        movie6.setGenresList(bpGenres);
        mockMovies.add(movie6);
        
        Movie movie7 = new Movie();
        movie7.setMovieId(7L);
        movie7.setTitle("Gladiator 2");
        movie7.setDuration(160);
        movie7.setMovieUrl("https://image.tmdb.org/t/p/w500/2cxhvwyEwRlysAmRH4iodkvo0z5.jpg");
        movie7.setBackdropUrl("https://image.tmdb.org/t/p/original/euYIwmwkmz95mnXvufEmbL6ovhZ.jpg");
        movie7.setDescription("Years after witnessing the death of the revered hero Maximus at the hands of his uncle, Lucius is forced to enter the Colosseum after his home is conquered by the tyrannical Emperors who now lead Rome with an iron fist. With rage in his heart and the future of the Empire at stake, Lucius must look to his past to find strength and honor to return the glory of Rome to its people.");
        movie7.setReleaseDate(now.plusDays(45));
        movie7.setActive(false);
        
        // Add genres for Gladiator
        List<Genres> gladiatorGenres = new ArrayList<>();
        Genres actionGlad = new Genres();
        actionGlad.setGenresId(13L);
        actionGlad.setGenresName("Action");
        actionGlad.setMovie(movie7);
        gladiatorGenres.add(actionGlad);
        
        Genres dramaGlad = new Genres();
        dramaGlad.setGenresId(14L);
        dramaGlad.setGenresName("Drama");
        dramaGlad.setMovie(movie7);
        gladiatorGenres.add(dramaGlad);
        
        movie7.setGenresList(gladiatorGenres);
        mockMovies.add(movie7);
        
        return mockMovies;
    }
    
    private Movie createMockMovieById(Long movieId) {
        java.time.OffsetDateTime now = java.time.OffsetDateTime.now();
        Movie movie = new Movie();
        movie.setMovieId(movieId);
        
        switch (movieId.intValue()) {
            case 1:
                movie.setTitle("Dune: Part Two");
                movie.setDuration(166);
                movie.setMovieUrl("https://image.tmdb.org/t/p/w500/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg");
                movie.setBackdropUrl("https://image.tmdb.org/t/p/original/xOMo8BRK7PfcJv9JCnx7s5hj0PX.jpg");
                movie.setTrailerUrl("https://www.youtube.com/embed/Way9Dexny3w");
                movie.setDescription("Paul Atreides unites with Chani and the Fremen while on a warpath of revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the known universe, he endeavors to prevent a terrible future only he can foresee.");
                movie.setReleaseDate(now.minusDays(30));
                movie.setRating(8.5);
                movie.setLanguage("English");
                movie.setDirector("Denis Villeneuve");
                movie.setCast("Timothée Chalamet, Zendaya, Rebecca Ferguson");
                movie.setAgeRating("PG-13");
                movie.setActive(true);
                break;
                
            case 2:
                movie.setTitle("Oppenheimer");
                movie.setDuration(180);
                movie.setMovieUrl("https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg");
                movie.setBackdropUrl("https://image.tmdb.org/t/p/original/rLb2cwF3Pazuxaj0sRXQ037tGI1.jpg");
                movie.setTrailerUrl("https://www.youtube.com/embed/uYPbbksJxIg");
                movie.setDescription("The story of J. Robert Oppenheimer's role in the development of the atomic bomb during World War II. A gripping biographical drama that explores the moral complexities of scientific discovery and its devastating consequences.");
                movie.setReleaseDate(now.minusDays(60));
                movie.setRating(8.3);
                movie.setLanguage("English");
                movie.setDirector("Christopher Nolan");
                movie.setCast("Cillian Murphy, Emily Blunt, Robert Downey Jr.");
                movie.setAgeRating("R");
                movie.setActive(true);
                break;
                
            case 3:
                movie.setTitle("Spider-Man: Across the Spider-Verse");
                movie.setDuration(140);
                movie.setMovieUrl("https://image.tmdb.org/t/p/w500/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg");
                movie.setBackdropUrl("https://image.tmdb.org/t/p/original/nGxUxi3PfXDRm7Vg95VBNgNM8yc.jpg");
                movie.setTrailerUrl("https://www.youtube.com/embed/cqGjhVJWtEg");
                movie.setDescription("After reuniting with Gwen Stacy, Brooklyn's full-time, friendly neighborhood Spider-Man is catapulted across the Multiverse, where he encounters the Spider Society, a team of Spider-People charged with protecting the Multiverse's very existence.");
                movie.setReleaseDate(now.minusDays(45));
                movie.setRating(8.7);
                movie.setLanguage("English");
                movie.setDirector("Joaquim Dos Santos, Kemp Powers, Justin K. Thompson");
                movie.setCast("Shameik Moore, Hailee Steinfeld, Brian Tyree Henry");
                movie.setAgeRating("PG");
                movie.setActive(true);
                break;
                
            case 4:
                movie.setTitle("The Super Mario Bros. Movie");
                movie.setDuration(92);
                movie.setMovieUrl("https://image.tmdb.org/t/p/w500/qNBAXBIQlnOThrVvA6mTahRTvSr.jpg");
                movie.setBackdropUrl("https://image.tmdb.org/t/p/original/9n2tJBplPbgR2ca05hS5CKXwP2c.jpg");
                movie.setTrailerUrl("https://www.youtube.com/embed/TnGl01FkMMo");
                movie.setDescription("While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world. But when the brothers are separated, Mario embarks on an epic quest to find Luigi.");
                movie.setReleaseDate(now.minusDays(90));
                movie.setRating(7.0);
                movie.setLanguage("English");
                movie.setDirector("Aaron Horvath, Michael Jelenic");
                movie.setCast("Chris Pratt, Anya Taylor-Joy, Charlie Day");
                movie.setAgeRating("PG");
                movie.setActive(true);
                break;
                
            case 5:
                movie.setTitle("Joker: Folie à Deux");
                movie.setDuration(150);
                movie.setMovieUrl("https://image.tmdb.org/t/p/w500/if8QiqCI7WAGImKcJCfBp6VTyKA.jpg");
                movie.setBackdropUrl("https://image.tmdb.org/t/p/original/iKSbqAamDzgUgOIFoMCmQwL0Yyp.jpg");
                movie.setTrailerUrl("https://www.youtube.com/embed/fiqqAI0e4Nc");
                movie.setDescription("Arthur Fleck is institutionalized at Arkham awaiting trial for his crimes as Joker. While struggling with his dual identity, Arthur not only stumbles upon true love, but also finds the music that's always been inside him.");
                movie.setReleaseDate(now.plusDays(30));
                movie.setRating(6.5);
                movie.setLanguage("English");
                movie.setDirector("Todd Phillips");
                movie.setCast("Joaquin Phoenix, Lady Gaga, Brendan Gleeson");
                movie.setAgeRating("R");
                movie.setActive(false);
                break;
                
            case 6:
                movie.setTitle("Black Panther: Wakanda Forever");
                movie.setDuration(161);
                movie.setMovieUrl("https://image.tmdb.org/t/p/w500/sv1xJUazXeYqALzczSZ3O6nkH75.jpg");
                movie.setBackdropUrl("https://image.tmdb.org/t/p/original/yYrvN5WFeGYjJnRzhY0QXuo4Isw.jpg");
                movie.setTrailerUrl("https://www.youtube.com/embed/_Z3QKkl1WyM");
                movie.setDescription("Queen Ramonda, Shuri, M'Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T'Challa's death. As the Wakandans strive to embrace their next chapter, the heroes must band together with the help of War Dog Nakia and Everett Ross to forge a new path for the kingdom of Wakanda.");
                movie.setReleaseDate(now.plusDays(15));
                movie.setRating(6.7);
                movie.setLanguage("English");
                movie.setDirector("Ryan Coogler");
                movie.setCast("Angela Bassett, Letitia Wright, Lupita Nyong'o");
                movie.setAgeRating("PG-13");
                movie.setActive(false);
                break;
                
            case 7:
                movie.setTitle("The Batman");
                movie.setDuration(176);
                movie.setMovieUrl("https://image.tmdb.org/t/p/w500/74xTEgt7R36Fpooo50r9T25onhq.jpg");
                movie.setBackdropUrl("https://image.tmdb.org/t/p/original/b0PlHJr0f6xtSVHXnOHy3SYLYB3.jpg");
                movie.setTrailerUrl("https://www.youtube.com/embed/mqqft2x_Aa4");
                movie.setDescription("When a sadistic serial killer begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement.");
                movie.setReleaseDate(now.minusDays(120));
                movie.setRating(7.8);
                movie.setLanguage("English");
                movie.setDirector("Matt Reeves");
                movie.setCast("Robert Pattinson, Zoë Kravitz, Jeffrey Wright");
                movie.setAgeRating("PG-13");
                movie.setActive(true);
                break;
                
            case 8:
                movie.setTitle("Top Gun: Maverick");
                movie.setDuration(131);
                movie.setMovieUrl("https://image.tmdb.org/t/p/w500/62HCnUTziyWcpDaBO2i1DX17ljH.jpg");
                movie.setBackdropUrl("https://image.tmdb.org/t/p/original/odJ4hx6g6vBt4lBWKFD1tI8WS4x.jpg");
                movie.setTrailerUrl("https://www.youtube.com/embed/qSqVVswa420");
                movie.setDescription("After more than thirty years of service as one of the Navy's top aviators, and dodging the advancement in rank that would ground him, Pete 'Maverick' Mitchell finds himself training a detachment of TOP GUN graduates for a specialized mission the likes of which no living pilot has ever seen.");
                movie.setReleaseDate(now.minusDays(180));
                movie.setRating(8.2);
                movie.setLanguage("English");
                movie.setDirector("Joseph Kosinski");
                movie.setCast("Tom Cruise, Jennifer Connelly, Miles Teller");
                movie.setAgeRating("PG-13");
                movie.setActive(true);
                break;
                
            case 9:
                movie.setTitle("Avengers: Endgame");
                movie.setDuration(181);
                movie.setMovieUrl("https://image.tmdb.org/t/p/w500/or06FN3Dka5tukK1e9sl16pB3iy.jpg");
                movie.setBackdropUrl("https://image.tmdb.org/t/p/original/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg");
                movie.setTrailerUrl("https://www.youtube.com/embed/TcMBFSGVi1c");
                movie.setDescription("After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all.");
                movie.setReleaseDate(now.minusDays(200));
                movie.setRating(8.4);
                movie.setLanguage("English");
                movie.setDirector("Anthony Russo, Joe Russo");
                movie.setCast("Robert Downey Jr., Chris Evans, Mark Ruffalo");
                movie.setAgeRating("PG-13");
                movie.setActive(true);
                break;
                
            default:
                // Default movie for any other ID
                movie.setTitle("Coming Soon");
                movie.setDuration(120);
                movie.setMovieUrl("https://image.tmdb.org/t/p/w500/placeholder.jpg");
                movie.setDescription("Movie details will be available soon.");
                movie.setReleaseDate(now.plusDays(60));
                movie.setRating(7.0);
                movie.setLanguage("English");
                movie.setDirector("TBA");
                movie.setCast("TBA");
                movie.setAgeRating("TBA");
                movie.setActive(false);
                break;
        }
        
        // Add default genres
        List<Genres> defaultGenres = new ArrayList<>();
        Genres defaultGenre = new Genres();
        defaultGenre.setGenresId(1L);
        defaultGenre.setGenresName("Action");
        defaultGenre.setMovie(movie);
        defaultGenres.add(defaultGenre);
        movie.setGenresList(defaultGenres);
        
        return movie;
    }

    @PostMapping("/movie/{movieId}/update")
    public String updateMovie(@PathVariable(name = "movieId") Long movieId,
                              @RequestBody MoviePostDto moviePostDto) {
        movieService.updatePartialMovie(movieId, moviePostDto);
        return "";
    }

    @PostMapping("/movie/new")
    public String addNewMovie(@RequestBody MoviePostDto moviePostDto) {
        movieService.addNewMovie(moviePostDto);
        return "";
    }

    @PostMapping("/movie/{movieId}/upload-image")
    public String uploadMovieImage (
            @PathVariable(name = "movieId") Long movieId,
            @RequestParam MultipartFile multipartFile) {
        try {
            movieService.uploadMovieImage(movieId, multipartFile);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return "";
    }

    @DeleteMapping("/movie/{movieId}/delete")
    public String deleteMovie (@PathVariable(name = "movieId") Long movieId) {
        movieService.deleteMovieById(movieId);
        return "";
    }
    
    @GetMapping("/movies/{id}")
    public String showMovieDetails(@PathVariable("id") Long movieId, Model model) {
        try {
            System.out.println("=== DEBUG: Movie ID = " + movieId); // Debug log
            
            // Tìm movie từ database
            List<Movie> allMovies = movieService.getAllMovies();
            
            // Nếu database rỗng, tạo mock data
            if (allMovies.isEmpty()) {
                allMovies = createMockMoviesWithGenres();
            }
            
            Movie movie = allMovies.stream()
                    .filter(m -> m.getMovieId().equals(movieId))
                    .findFirst()
                    .orElse(createMockMovieById(movieId)); // Tạo movie riêng biệt nếu không tìm thấy
            
            System.out.println("=== DEBUG: Found movie = " + (movie != null ? movie.getTitle() : "NULL")); // Debug log
            
            if (movie == null) {
                // Redirect về movies page nếu không tìm thấy
                return "redirect:/movies";
            }
            
            model.addAttribute("movie", movie);
            return "movie-details";
            
        } catch (Exception e) {
            System.out.println("=== DEBUG: Error = " + e.getMessage()); // Debug log
            log.error("Error loading movie details: {}", e.getMessage());
            return "redirect:/movies";
        }
    }
    
    @GetMapping("/movies/{id}/book")
    public String showBookingPage(@PathVariable("id") Long movieId, Model model, HttpSession session) {
        // Kiểm tra đăng nhập
        if (session.getAttribute("userName") == null) {
            return "redirect:/login";
        }
        
        try {
            List<Movie> allMovies = movieService.getAllMovies();
            Movie movie = allMovies.stream()
                    .filter(m -> m.getMovieId().equals(movieId))
                    .findFirst()
                    .orElse(null);
            
            if (movie == null) {
                return "redirect:/movies";
            }
            
            model.addAttribute("movie", movie);
            return "showtimes-by-movie";
            
        } catch (Exception e) {
            log.error("Error loading booking page: {}", e.getMessage());
            return "redirect:/movies";
        }
    }

//    @GetMapping("/movies")
//    public String showAllMovies(Model model) {
//        // Create a large master list of mock movies
//        List<Movie> allMovies = List.of(
//                new Movie(1L, "Dune: Part Two", "Sci-Fi", 166, "/images/movie_posters/dune2.jpg", "NOW_SHOWING"),
//                new Movie(2L, "Oppenheimer", "Drama", 180, "/images/movie_posters/oppenheimer.jpg", "NOW_SHOWING"),
//                new Movie(3L, "The Super Mario Bros. Movie", "Animation", 92, "/images/movie_posters/mario.jpg", "NOW_SHOWING"),
//                new Movie(4L, "Spider-Man: Across the Spider-Verse", "Animation", 140, "/images/movie_posters/spiderman.jpg", "NOW_SHOWING"),
//                new Movie(5L, "The Creator", "Sci-Fi", 133, "/images/movie_posters/the_creator.jpg", "NOW_SHOWING"),
//                new Movie(6L, "Past Lives", "Drama", 105, "/images/movie_posters/past_lives.jpg", "NOW_SHOWING"),
//                new Movie(7L, "John Wick: Chapter 4", "Action", 169, "/images/movie_posters/john_wick4.jpg", "NOW_SHOWING"),
//                new Movie(8L, "Barbie", "Comedy", 114, "/images/movie_posters/barbie.jpg", "NOW_SHOWING"),
//                new Movie(9L, "Joker: Folie à Deux", "Thriller", 150, "/images/movie_posters/joker2.jpg", "COMING_SOON"),
//                new Movie(10L, "Gladiator 2", "Action", 160, "/images/movie_posters/gladiator2.jpg", "COMING_SOON"),
//                new Movie(11L, "Inside Out 2", "Animation", 100, "/images/movie_posters/inside_out2.jpg", "COMING_SOON")
//        );
//
//        // Filter the list into two separate lists for the tabs
//        List<Movie> nowShowing = allMovies.stream()
//                .filter(movie -> "NOW_SHOWING".equals(movie.getStatus()))
//                .toList();
//
//        List<Movie> comingSoon = allMovies.stream()
//                .filter(movie -> "COMING_SOON".equals(movie.getStatus()))
//                .toList();
//
//        // Add both lists to the model so the template can access them
//        model.addAttribute("nowShowingMovies", nowShowing);
//        model.addAttribute("comingSoonMovies", comingSoon);
//
//        return "movies";
//    }
//
//    @GetMapping("/movies/{id}")
//    public String showMovieDetails(@PathVariable("id") Long movieId, Model model) {
//        // In a real app, you'd fetch this movie from the database using the movieId.
//        // For now, we create a detailed mock movie object.
//        Movie mockMovie = new Movie(
//                1L, "Dune: Part Two", "Sci-Fi", 166, "/images/movie_posters/dune2.jpg", "NOW_SHOWING",
//                "PG-13",
//                "Paul Atreides unites with Chani and the Fremen while on a warpath of revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the known universe, he endeavors to prevent a terrible future only he can foresee.",
//                "https://www.youtube.com/embed/U2Qp5pL3ovA" // YouTube embed link
//        );
//
//        // Create a mock list of available showtimes for this movie
//        model.addAttribute("movie", mockMovie);
//        // model.addAttribute("showtimes", mockShowtimes); // DELETE THIS
//
//        return "movie-details";
//    }


}