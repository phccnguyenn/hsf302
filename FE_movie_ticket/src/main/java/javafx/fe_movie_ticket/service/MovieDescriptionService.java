package javafx.fe_movie_ticket.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MovieDescriptionService {
    
    // Map chứa mô tả các phim cho Hero Slides
    private static final Map<String, String> MOVIE_DESCRIPTIONS = new HashMap<>();
    
    static {
        MOVIE_DESCRIPTIONS.put("dune: part two", "Follow the mythic journey of Paul Atreides as he unites with Chani and the Fremen while on a path of revenge against the conspirators who destroyed his family.");
        MOVIE_DESCRIPTIONS.put("oppenheimer", "The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb during World War II.");
        MOVIE_DESCRIPTIONS.put("avengers: endgame", "After the devastating events of Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more.");
        MOVIE_DESCRIPTIONS.put("the batman", "In his second year of fighting crime, Batman uncovers corruption in Gotham City that connects to his own family while facing a serial killer known as the Riddler.");
        MOVIE_DESCRIPTIONS.put("top gun: maverick", "After more than thirty years of service as one of the Navy's top aviators, Pete Mitchell is where he belongs, pushing the envelope as a courageous test pilot.");
        MOVIE_DESCRIPTIONS.put("spider-man: across the spider-verse", "Miles Morales catapults across the Multiverse, where he encounters a team of Spider-People charged with protecting existence itself.");
        MOVIE_DESCRIPTIONS.put("the super mario bros. movie", "A plumber named Mario travels through an underground labyrinth with his brother Luigi, trying to save a captured princess.");
        MOVIE_DESCRIPTIONS.put("black panther: wakanda forever", "Queen Ramonda, Shuri, M'Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T'Challa's death.");
        
        // Coming Soon movies
        MOVIE_DESCRIPTIONS.put("guardians of the galaxy vol. 3", "Peter Quill, still reeling from the loss of Gamora, must rally his team around him to defend the universe along with protecting one of their own.");
        MOVIE_DESCRIPTIONS.put("fast x", "Over many missions and against impossible odds, Dom Toretto and his family have outsmarted, out-nerved and outdriven every foe in their path.");
        MOVIE_DESCRIPTIONS.put("john wick: chapter 4", "John Wick uncovers a path to defeating The High Table. But before he can earn his freedom, Wick must face off against a new enemy.");
        MOVIE_DESCRIPTIONS.put("transformers: rise of the beasts", "A '90s globetrotting adventure that introduces the Maximals, Predacons, and Terrorcons to the existing battle on earth between Autobots and Decepticons.");
    }
    
    public String getMovieDescription(String movieTitle) {
        return MOVIE_DESCRIPTIONS.getOrDefault(movieTitle.toLowerCase(), 
                "An exciting cinematic experience awaiting you in theaters.");
    }
    
    // Lấy backdrop URL (ảnh ngang chất lượng cao) cho hero slides
    public String getBackdropUrl(String movieTitle) {
        switch (movieTitle.toLowerCase()) {
            case "dune: part two":
                return "https://image.tmdb.org/t/p/w1280/xt0j7z4LP0PLatLkV3FSxWI8IF9.jpg";
            case "oppenheimer":
                return "https://image.tmdb.org/t/p/w1280/ncKCQVXgk4BcQV6XbvesgZ2zJJW.jpg";
            case "avengers: endgame":
                return "https://image.tmdb.org/t/p/w1280/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg";
            case "the batman":
                return "https://image.tmdb.org/t/p/w1280/b0PlSFdDwbyK0cf5RxwDpaOJQvQ.jpg";
            case "top gun: maverick":
                return "https://image.tmdb.org/t/p/w1280/odM92ap21JJaus0O9ODG0qd0wnS.jpg";
            case "spider-man: across the spider-verse":
                return "https://image.tmdb.org/t/p/w1280/4HodYYKEIsGOdinkGi2Ucz6X9i0.jpg";
            case "the super mario bros. movie":
                return "https://image.tmdb.org/t/p/w1280/9n2tJBplPbgR2ca05hS5CKXwP2c.jpg";
            case "black panther: wakanda forever":
                return "https://image.tmdb.org/t/p/w1280/yYrvN5WFeGYjJnRzhY0QXuo4Isw.jpg";
            default:
                return "https://via.placeholder.com/1280x720/1F1F1F/FFFFFF?text=COMING+SOON";
        }
    }
}