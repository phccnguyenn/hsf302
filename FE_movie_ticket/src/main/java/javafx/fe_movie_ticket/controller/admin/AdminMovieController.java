package javafx.fe_movie_ticket.controller.admin;

import javafx.fe_movie_ticket.entity.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/movies") // CHỊU TRÁCH NHIỆM CHO CÁC URL BẮT ĐẦU BẰNG /admin/movies
public class AdminMovieController {

    /**
     * Phương thức này xử lý request GET đến /admin/movies.
     * Nhiệm vụ duy nhất của nó là lấy và hiển thị danh sách tất cả các bộ phim.
     * @param model Đối tượng để truyền dữ liệu đến view.
     * @return Tên của template Thymeleaf để render.
     */
    @GetMapping
    public String listMovies(Model model) {
        // Dữ liệu giả (sau này sẽ lấy từ database)
        List<Movie> allMovies = List.of(
                new Movie(1L, "Dune: Part Two", "Sci-Fi", 166, null, "NOW_SHOWING"),
                new Movie(2L, "Oppenheimer", "Drama", 180, null, "NOW_SHOWING"),
                new Movie(9L, "Joker: Folie à Deux", "Thriller", 150, null, "COMING_SOON")
        );

        model.addAttribute("movies", allMovies);

        // Trả về đường dẫn đến template trong thư mục admin
        return "admin/movies_list";
    }

    // SAU NÀY, CÁC PHƯƠNG THỨC KHÁC LIÊN QUAN ĐẾN PHIM SẼ Ở ĐÂY:
    // @GetMapping("/new")
    // public String showAddMovieForm() { ... }

    // @PostMapping("/save")
    // public String saveMovie(...) { ... }

    // @GetMapping("/edit/{id}")
    // public String showEditMovieForm(...) { ... }
}