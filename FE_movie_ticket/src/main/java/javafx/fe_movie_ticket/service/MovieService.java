package javafx.fe_movie_ticket.service;

import javafx.fe_movie_ticket.entity.Genres;
import javafx.fe_movie_ticket.entity.Movie;
import javafx.fe_movie_ticket.entity.dto.GenresPostDto;
import javafx.fe_movie_ticket.entity.dto.MoviePostDto;
import javafx.fe_movie_ticket.repository.FileSystemRepository;
import javafx.fe_movie_ticket.repository.GenresRepository;
import javafx.fe_movie_ticket.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private static final String URL_BASE = "http://localhost:8080";
    private final FileSystemRepository fileSystemRepository;
    private final MovieRepository movieRepository;
    private final GenresRepository genresRepository;

    public List<Genres> getAllGenres() {
        return genresRepository.findAll();
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    private Genres createGenres(GenresPostDto genresPostDto) {
        Genres genres = new Genres();
        genres.setGenresName(genresPostDto.getGenresName());
        return genresRepository.save(genres);
    }

    public Movie addNewMovie(MoviePostDto moviePostDto) {
        Movie movie = new Movie();
        movie.setTitle(moviePostDto.getTitle());
        movie.setDuration(moviePostDto.getDuration());
        movie.setReleaseDate(moviePostDto.getReleaseDate());
        movie.setActive(moviePostDto.getIsActive());

        List<Genres> genresList = moviePostDto.getGenresList().stream()
                .map(genresPostDto ->
                        genresRepository.findByGenresName(genresPostDto.getGenresName())
                                .orElseGet(() -> createGenres(genresPostDto))
                ).toList();

        movie.setGenresList(genresList);
        return movieRepository.save(movie);
    }

    public void uploadMovieImage(Long movieId, MultipartFile multipartFile) throws IOException {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("MOVIE_WITH_" + movieId + "_NOT_FOUND"));

        try {
            // Extract file name and content
            String filename = multipartFile.getOriginalFilename();
            byte[] content = multipartFile.getBytes();

            // Save to filesystem
            String savedPath = fileSystemRepository.persistMovieFile(filename, content);
            log.info("Movie image uploaded successfully: {}", savedPath);

            String filePathStr = savedPath.substring(1).replace("\\", "/");
            String fileUrl = URL_BASE + filePathStr;

            movie.setMovieUrl(fileUrl);
            movieRepository.save(movie);

        } catch (IOException e) {
            log.error("Failed to upload movie image: {}", e.getMessage());
            throw new RuntimeException("Failed to upload movie image", e);
        }

    }

    public void updatePartialMovie(Long movieId, MoviePostDto moviePostDto) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("MOVIE_WITH_" + movieId + "_NOT_FOUND"));

        if (moviePostDto.getTitle() != null && !moviePostDto.getTitle().equals(movie.getTitle())) {
            movie.setTitle(moviePostDto.getTitle());
        }

        if (moviePostDto.getDuration() != null && !moviePostDto.getDuration().equals(movie.getDuration())) {
            movie.setDuration(moviePostDto.getDuration());
        }

        if (moviePostDto.getReleaseDate() != null && !moviePostDto.getReleaseDate().equals(movie.getReleaseDate())) {
            movie.setReleaseDate(moviePostDto.getReleaseDate());
        }

        movie.setActive(moviePostDto.getIsActive());

        movieRepository.save(movie);
    }

    public void deleteMovieById(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("MOVIE_WITH_" + movieId + "_NOT_FOUND"));
        movieRepository.delete(movie);
    }


}
