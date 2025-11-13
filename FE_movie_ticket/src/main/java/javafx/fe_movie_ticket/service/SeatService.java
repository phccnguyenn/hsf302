package javafx.fe_movie_ticket.service;

import javafx.fe_movie_ticket.entity.Auditorium;
import javafx.fe_movie_ticket.entity.Seat;
import javafx.fe_movie_ticket.entity.enumeration.SeatType;
import javafx.fe_movie_ticket.repository.AuditoriumRepository;
import javafx.fe_movie_ticket.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class SeatService {

    private final SeatRepository seatRepository;

    public Seat createSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    public List<Seat> getSeatsByAuditorium(Long auditoriumId) {
        return seatRepository.findByAuditoriumAuditoriumIdAndIsActive(auditoriumId, true);
    }

    public void deactivateSeat(Long seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + seatId));
        seat.setActive(false);
        seatRepository.save(seat);
    }



}
