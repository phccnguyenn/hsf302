package javafx.fe_movie_ticket.controller;

import javafx.fe_movie_ticket.entity.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TestController {

    @GetMapping("/test")
    public String testPage(HttpSession session, Model model) {
        String userType = (String) session.getAttribute("userType");
        String userName = (String) session.getAttribute("userName");
        String userEmail = (String) session.getAttribute("userEmail");
        
        model.addAttribute("userType", userType != null ? userType : "Not logged in");
        model.addAttribute("userName", userName != null ? userName : "Guest");
        model.addAttribute("userEmail", userEmail != null ? userEmail : "No email");
        
        return "test-page";
    }

    @GetMapping("/test-info")
    public String testInfo(Model model) {
        // Mock admin credentials
        model.addAttribute("adminEmail", "admin@cineplex.vn");
        model.addAttribute("adminPassword", "admin123");
        
        // Mock user credentials  
        model.addAttribute("userEmail", "user@example.com");
        model.addAttribute("userPassword", "user123");
        
        // Additional test accounts
        model.addAttribute("managerEmail", "manager@cineplex.vn");
        model.addAttribute("managerPassword", "manager123");
        
        model.addAttribute("staffEmail", "staff@cineplex.vn");
        model.addAttribute("staffPassword", "staff123");
        
        return "test-info";
    }

    @GetMapping("/admin-test")  
    public String adminTest() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/test/local-storage")
    public String showLocalStorageTest(Model model) {
        // Mock data for testing hero slides and local storage
        
        Movie movie1 = new Movie();
        movie1.setMovieId(1L);
        movie1.setTitle("Dune: Part Two");
        movie1.setDuration(166);
        movie1.setMovieUrl("https://image.tmdb.org/t/p/w500/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg");
        movie1.setBackdropUrl("https://image.tmdb.org/t/p/w1280/xt0j7z4LP0PLatLkV3FSxWI8IF9.jpg");
        movie1.setDescription("Follow the mythic journey of Paul Atreides as he unites with Chani and the Fremen while on a path of revenge against the conspirators who destroyed his family.");

        Movie movie2 = new Movie();
        movie2.setMovieId(2L);
        movie2.setTitle("Oppenheimer");
        movie2.setDuration(180);
        movie2.setMovieUrl("https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg");
        movie2.setBackdropUrl("https://image.tmdb.org/t/p/w1280/ncKCQVXgk4BcQV6XbvesgZ2zJJW.jpg");
        movie2.setDescription("The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb during World War II.");

        Movie movie3 = new Movie();
        movie3.setMovieId(5L);
        movie3.setTitle("Avengers: Endgame");
        movie3.setDuration(181);
        movie3.setMovieUrl("https://image.tmdb.org/t/p/w500/or06FN3Dka5tukK1e9sl16pB3iy.jpg");
        movie3.setBackdropUrl("https://image.tmdb.org/t/p/w1280/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg");
        movie3.setDescription("After the devastating events of Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more.");

        Movie movie4 = new Movie();
        movie4.setMovieId(4L);
        movie4.setTitle("Spider-Man: Across the Spider-Verse");
        movie4.setDuration(140);
        movie4.setMovieUrl("https://image.tmdb.org/t/p/w500/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg");
        movie4.setBackdropUrl("https://image.tmdb.org/t/p/w1280/4HodYYKEIsGOdinkGi2Ucz6X9i0.jpg");
        movie4.setDescription("Miles Morales catapults across the Multiverse, where he encounters a team of Spider-People charged with protecting existence itself.");

        Movie movie5 = new Movie();
        movie5.setMovieId(6L);
        movie5.setTitle("The Batman");
        movie5.setDuration(176);
        movie5.setMovieUrl("https://image.tmdb.org/t/p/w500/74xTEgt7R36Fpooo50r9T25onhq.jpg");
        movie5.setBackdropUrl("https://image.tmdb.org/t/p/w1280/b0PlSFdDwbyK0cf5RxwDpaOJQvQ.jpg");
        movie5.setDescription("In his second year of fighting crime, Batman uncovers corruption in Gotham City that connects to his own family while facing a serial killer known as the Riddler.");

        Movie movie6 = new Movie();
        movie6.setMovieId(7L);
        movie6.setTitle("Top Gun: Maverick");
        movie6.setDuration(130);
        movie6.setMovieUrl("https://image.tmdb.org/t/p/w500/62HCnUTziyWcpDaBO2i1DX17ljH.jpg");
        movie6.setBackdropUrl("https://image.tmdb.org/t/p/w1280/odM92ap21JJaus0O9ODG0qd0wnS.jpg");
        movie6.setDescription("After more than thirty years of service as one of the Navy's top aviators, Pete Mitchell is where he belongs, pushing the envelope as a courageous test pilot.");

        List<Movie> heroSlides = List.of(movie1, movie2, movie3, movie4);
        List<Movie> nowShowingMovies = List.of(movie1, movie2, movie3, movie4, movie5, movie6);

        model.addAttribute("heroSlides", heroSlides);
        model.addAttribute("nowShowingMovies", nowShowingMovies);
        
        return "test-local-storage";
    }
}