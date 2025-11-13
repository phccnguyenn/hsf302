package javafx.fe_movie_ticket.repository;

import javafx.fe_movie_ticket.entity.Showtime;
import javafx.fe_movie_ticket.entity.enumeration.SeatStatus;
import javafx.fe_movie_ticket.entity.enumeration.ShowtimeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    List<Showtime> findAll();

    List<Showtime> findByAuditoriumCinemaCinemaIdAndStartsAtAfter(Long cinemaId, LocalDateTime dateTime);

    // Sửa từ findByMovieId thành findByMovieMovieId để khớp với entity
    List<Showtime> findByMovieMovieIdAndStartsAtAfter(Long movieId, LocalDateTime dateTime);

    List<Showtime> findByStartsAtAfterAndStatusOrderByStartsAt(LocalDateTime dateTime, ShowtimeStatus status);



    List<Showtime> findByAuditoriumAuditoriumIdAndStatusAndStartsAtBetweenOrEndsAtBetween(
            Long auditoriumId, ShowtimeStatus status,
            LocalDateTime start1, LocalDateTime end1,
            LocalDateTime start2, LocalDateTime end2);

    boolean existsByMovieMovieIdAndStartsAtAfter(Long movieId, LocalDateTime dateTime);
    List<Showtime> findByMovieMovieIdAndAuditoriumCinemaCinemaIdAndStartsAtAfter(
            Long movieId, Long cinemaId, LocalDateTime now);
}
