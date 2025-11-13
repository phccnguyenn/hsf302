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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class SeatService {

    private final SeatRepository seatRepository;
    private final AuditoriumRepository auditoriumRepository;

    public Seat createSeat(Seat seat) {
        // Validate auditorium
        Auditorium auditorium = auditoriumRepository.findById(seat.getAuditorium().getAuditoriumId())
                .orElseThrow(() -> new RuntimeException("AUDITORIUM_NOT_FOUND"));

        // Kiểm tra ghế đã tồn tại
        if (seatRepository.existsByAuditoriumAuditoriumIdAndRowLabelAndSeatNumberAndIsActiveTrue(
                auditorium.getAuditoriumId(), seat.getRowLabel(), seat.getSeatNumber())) {
            throw new RuntimeException("SEAT_ALREADY_EXISTS");
        }

        seat.setAuditorium(auditorium);
        return seatRepository.save(seat);
    }
    public List<Seat> getSeatsByRow(Long auditoriumId, String rowLabel) {
        return seatRepository.findByRowLabelAndAuditoriumAuditoriumIdAndIsActiveTrue(rowLabel, auditoriumId);
    }

    public void deactivateSeat(Long seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + seatId));
        seat.setActive(false);
        seatRepository.save(seat);
    }

    public List<Seat> getSeatsByType(SeatType seatType) {
        return seatRepository.findBySeatTypeAndIsActiveTrue(seatType);
    }

    public void activateSeat(Long seatId) {
        Seat seat = getSeatById(seatId);
        seat.setActive(true);
        seatRepository.save(seat);
    }

    public Seat getSeatById(Long seatId) {
        return seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("SEAT_NOT_FOUND"));
    }

    public List<Seat> getSeatsByAuditorium(Long auditoriumId) {
        return seatRepository.findByAuditoriumAuditoriumIdAndIsActiveTrue(auditoriumId);
    }

    public List<String> getDistinctRows(Long auditoriumId) {
        List<Seat> seats = getSeatsByAuditorium(auditoriumId);
        return seats.stream()
                .map(Seat::getRowLabel)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

}
