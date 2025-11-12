package javafx.fe_movie_ticket.service;

import jakarta.transaction.Transactional;
import javafx.fe_movie_ticket.entity.Showtime;
import javafx.fe_movie_ticket.entity.enumeration.ShowtimeStatus;
import javafx.fe_movie_ticket.repository.AuditoriumRepository;
import javafx.fe_movie_ticket.repository.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ShowtimeService {
    private final ShowtimeRepository showtimeRepository;
    private final AuditoriumRepository auditoriumRepository;



    public Showtime createShowtime(Showtime showtime) {

        if (showtime.getEndsAt().isBefore(showtime.getStartsAt())) {
            throw new RuntimeException("End time must be after start time");
        }

        List<Showtime> conflictingShowtimes = showtimeRepository
                .findConflictingShowtimes(showtime.getAuditorium().getAuditoriumId(),
                        showtime.getStartsAt(), showtime.getEndsAt()
                );

        if (!conflictingShowtimes.isEmpty()) {
            throw new RuntimeException("Auditorium is not available at this time");
        }
        return showtimeRepository.save(showtime);
    }

    public List<Showtime> getShowtimesByCinema(Long cinemaId) {
        return showtimeRepository.findByAuditoriumCinemaCinemaIdAndStartsAtAfter(
                cinemaId, LocalDateTime.now());
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
}
