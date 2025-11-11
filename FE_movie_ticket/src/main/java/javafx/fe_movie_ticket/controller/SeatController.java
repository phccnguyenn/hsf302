package javafx.fe_movie_ticket.controller;

import jakarta.validation.Valid;
import javafx.fe_movie_ticket.entity.Seat;
import javafx.fe_movie_ticket.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/admin/seats")
@Validated
@RequiredArgsConstructor
public class SeatController {


    private final SeatService seatService;

    @PostMapping
    public ResponseEntity<Seat> createSeat(@RequestBody Seat seat) {
        return ResponseEntity.ok(seatService.createSeat(seat));
    }

    @PutMapping("/{seatId}/price")
    public ResponseEntity<Seat> updatePrice(
            @PathVariable Long seatId,
            @RequestParam BigDecimal price) {
        return ResponseEntity.ok(seatService.updateSeatPrice(seatId, price));
    }

    @DeleteMapping("/{seatId}")
    public ResponseEntity<Void> deactivateSeat(@PathVariable Long seatId) {
        seatService.deactivateSeat(seatId);
        return ResponseEntity.ok().build();
    }
}
