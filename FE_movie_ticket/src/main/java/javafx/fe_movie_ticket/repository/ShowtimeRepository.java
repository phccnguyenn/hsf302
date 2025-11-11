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
    List<Showtime> findByAuditoriumCinemaCinemaIdAndStartsAtAfter(Long cinemaId, LocalDateTime dateTime);

    List<Showtime> findByMovieIdAndStartsAtAfter(Long movieId, LocalDateTime dateTime);

    List<Showtime> findByStartsAtAfterAndStatusOrderByStartsAt(LocalDateTime dateTime, ShowtimeStatus status);

    @Query("SELECT s FROM Showtime s WHERE s.auditorium.auditoriumId = :auditoriumId " +
            "AND s.status = 'ACTIVE' " +
            "AND ((s.startsAt BETWEEN :start AND :end) OR (s.endsAt BETWEEN :start AND :end))")
    List<Showtime> findConflictingShowtimes(@Param("auditoriumId") Long auditoriumId,
                                            @Param("start") LocalDateTime start,
                                            @Param("end") LocalDateTime end);

//
//    List<Showtime> findByShowtimeShowtimeId(Long showtimeId);
//
//    List<Showtime> findByShowtimeShowtimeIdAndStatus(Long showtimeId, SeatStatus status);
//
//    List<Showtime> findByShowtimeShowtimeIdAndSeatSeatIdIn(Long showtimeId, List<Long> seatIds);
//
//    Optional<Showtime> findByShowtimeShowtimeIdAndSeatSeatId(Long showtimeId, Long seatId);
}
