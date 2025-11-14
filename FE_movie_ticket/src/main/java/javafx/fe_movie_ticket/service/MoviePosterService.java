package javafx.fe_movie_ticket.service;

import org.springframework.stereotype.Service;

@Service
public class MoviePosterService {
    
    private static final String TMDB_BASE_URL = "https://image.tmdb.org/t/p/w500/";
    
    // TMDb poster paths cho các phim phổ biến
    public String getPosterUrl(String movieTitle) {
        switch (movieTitle.toLowerCase()) {
            // Phim hiện tại
            case "dune: part two":
                return TMDB_BASE_URL + "1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg";
            case "oppenheimer":
                return TMDB_BASE_URL + "8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg";
            case "the super mario bros. movie":
                return TMDB_BASE_URL + "qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg";
            case "spider-man: across the spider-verse":
                return TMDB_BASE_URL + "8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg";
            case "avengers: endgame":
                return TMDB_BASE_URL + "or06FN3Dka5tukK1e9sl16pB3iy.jpg";
            case "the batman":
                return TMDB_BASE_URL + "74xTEgt7R36Fpooo50r9T25onhq.jpg";
            case "top gun: maverick":
                return TMDB_BASE_URL + "62HCnUTziyWcpDaBO2i1DX17ljH.jpg";
            case "black panther: wakanda forever":
                return TMDB_BASE_URL + "sv1xJUazXeYqALzczSZ3O6nkH75.jpg";
            
            // Thêm phim mới 2024-2025
            case "guardians of the galaxy vol. 3":
                return TMDB_BASE_URL + "5YZbUmjbMa3ClvSW1Wj2yGmapfI.jpg";
            case "fast x":
                return TMDB_BASE_URL + "fiVW06jE7z9YnO4trhaMEdclSiC.jpg";
            case "transformers: rise of the beasts":
                return TMDB_BASE_URL + "gPbM0MK8CP8A174rmUwGsADNYKD.jpg";
            case "indiana jones and the dial of destiny":
                return TMDB_BASE_URL + "Af4bXE63pVsb2FtbW8uYIyPBadD.jpg";
            case "mission: impossible – dead reckoning part one":
                return TMDB_BASE_URL + "NNxYkU70HPurnNCSiCjYAmacwm.jpg";
            case "barbie":
                return TMDB_BASE_URL + "iuFNMS8U5cb6xfzi51Dbkovj7vM.jpg";
            case "john wick: chapter 4":
                return TMDB_BASE_URL + "vZloFAK7NmvMGKE7VkF5UHaz0I.jpg";
            case "scream vi":
                return TMDB_BASE_URL + "wDWwtvkRRlgTiUr6TyLSMZsRCjO.jpg";
            case "ant-man and the wasp: quantumania":
                return TMDB_BASE_URL + "qnqGbB22YJ7dSs4o6M7exTpNxPz.jpg";
            case "creed iii":
                return TMDB_BASE_URL + "cvsXj3I9Q2iyyIo95AecSd1tad7.jpg";
            
            // Phim Việt Nam
            case "mai":
                return TMDB_BASE_URL + "yh64qw9mgXBvlaWDi7Q9tpUBAvH.jpg";
            case "trạng tí":
                return TMDB_BASE_URL + "bQkpfnOaLy35dISFR2DQ4YN0HCA.jpg";
            case "lật mặt 6":
                return TMDB_BASE_URL + "xvzjJYg5geXBPnBNlWihMh0gnsE.jpg";
            
            default:
                return "/uploads/movie/img.png"; // fallback
        }
    }
    
    // Phương thức để lấy poster từ TMDb API (cần API key)
    public String fetchPosterFromAPI(String movieTitle) {
        // TODO: Implement TMDb API call
        // Cần API key từ https://www.themoviedb.org/settings/api
        return getPosterUrl(movieTitle);
    }
}