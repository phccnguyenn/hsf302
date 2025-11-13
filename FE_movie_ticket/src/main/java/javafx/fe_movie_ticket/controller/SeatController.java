package javafx.fe_movie_ticket.controller;

import jakarta.validation.Valid;
import javafx.fe_movie_ticket.entity.Seat;
import javafx.fe_movie_ticket.entity.enumeration.SeatType;
import javafx.fe_movie_ticket.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@Validated
@RequiredArgsConstructor
public class SeatController {


    private final SeatService seatService;

    @PostMapping
    public ResponseEntity<Seat> createSeat(@RequestBody Seat seat) {
        return ResponseEntity.ok(seatService.createSeat(seat));
    }

    @DeleteMapping("/{seatId}")
    public ResponseEntity<Void> deactivateSeat(@PathVariable Long seatId) {
        seatService.deactivateSeat(seatId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{seatId}")
    public ResponseEntity<Seat> getSeatById(@PathVariable Long seatId) {
        Seat seat = seatService.getSeatById(seatId);
        return ResponseEntity.ok(seat);
    }


    @GetMapping("/auditorium/{auditoriumId}")
    public ResponseEntity<List<Seat>> getSeatsByAuditorium(@PathVariable Long auditoriumId) {
        List<Seat> seats = seatService.getSeatsByAuditorium(auditoriumId);
        return ResponseEntity.ok(seats);
    }


    @GetMapping("/type/{seatType}")
    public ResponseEntity<List<Seat>> getSeatsByType(@PathVariable SeatType seatType) {
        List<Seat> seats = seatService.getSeatsByType(seatType);
        return ResponseEntity.ok(seats);
    }


    @GetMapping("/auditorium/{auditoriumId}/row/{rowLabel}")
    public ResponseEntity<List<Seat>> getSeatsByRow(
            @PathVariable Long auditoriumId,
            @PathVariable String rowLabel) {
        List<Seat> seats = seatService.getSeatsByRow(auditoriumId, rowLabel);
        return ResponseEntity.ok(seats);
    }




    @PatchMapping("/{seatId}/activate")
    public ResponseEntity<Void> activateSeat(@PathVariable Long seatId) {
        seatService.activateSeat(seatId);
        return ResponseEntity.ok().build();
    }




    @GetMapping("/auditorium/{auditoriumId}/rows")
    public ResponseEntity<List<String>> getDistinctRows(@PathVariable Long auditoriumId) {
        List<String> rows = seatService.getDistinctRows(auditoriumId);
        return ResponseEntity.ok(rows);
    }

}
