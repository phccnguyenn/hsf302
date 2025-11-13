package javafx.fe_movie_ticket.service;

import jakarta.transaction.Transactional;
import javafx.fe_movie_ticket.entity.Auditorium;
import javafx.fe_movie_ticket.entity.Movie;
import javafx.fe_movie_ticket.entity.Showtime;
import javafx.fe_movie_ticket.entity.enumeration.ShowtimeStatus;
import javafx.fe_movie_ticket.repository.AuditoriumRepository;
import javafx.fe_movie_ticket.repository.MovieRepository;
import javafx.fe_movie_ticket.repository.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ShowtimeService {
    private final ShowtimeRepository showtimeRepository;
    private final AuditoriumRepository auditoriumRepository;
    private final MovieRepository movieRepository;




    public Showtime createShowtime(Showtime showtime) {

        Movie movie = movieRepository.findById(showtime.getMovie().getMovieId())
                .orElseThrow(() -> new RuntimeException("MOVIE_NOT_FOUND"));


        Auditorium auditorium = auditoriumRepository.findById(showtime.getAuditorium().getAuditoriumId())
                .orElseThrow(() -> new RuntimeException("AUDITORIUM_NOT_FOUND"));



        List<Showtime> conflictingShowtimes = showtimeRepository
                .findByAuditoriumAuditoriumIdAndStatusAndStartsAtBetweenOrEndsAtBetween(
                        showtime.getAuditorium().getAuditoriumId(),
                        ShowtimeStatus.ACTIVE,
                        showtime.getStartsAt(), showtime.getEndsAt(),
                        showtime.getStartsAt(), showtime.getEndsAt());

        if (!conflictingShowtimes.isEmpty()) {
            throw new RuntimeException("AUDITORIUM_IS_NOT_AVAILABLE_AT_THIS_TIME");
        }

        showtime.setMovie(movie);
        showtime.setAuditorium(auditorium);
        showtime.setStatus(ShowtimeStatus.ACTIVE);

        return showtimeRepository.save(showtime);
    }


    public List<Showtime> getUpcomingShowtimes() {
        return showtimeRepository.findByStartsAtAfterAndStatusOrderByStartsAt(
                LocalDateTime.now(), ShowtimeStatus.ACTIVE);
    }


    public Showtime getShowtimeById(Long showtimeId) {
        return showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));
    }


    public Showtime updateShowtime(Long showtimeId, Showtime showtimeDetails) {
        Showtime showtime = getShowtimeById(showtimeId);

        showtime.setStartsAt(showtimeDetails.getStartsAt());
        showtime.setEndsAt(showtimeDetails.getEndsAt());
        showtime.setLanguage(showtimeDetails.getLanguage());

        return showtimeRepository.save(showtime);
    }
    public List<Showtime> getAllShowtimes() {
        return showtimeRepository.findAll();
    }

    public void deleteShowtime(Long showtimeId) {
        Showtime showtime = getShowtimeById(showtimeId);
        showtimeRepository.delete(showtime);
    }

}
