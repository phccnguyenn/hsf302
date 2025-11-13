package javafx.fe_movie_ticket.controller;

import javafx.fe_movie_ticket.entity.Showtime;
import javafx.fe_movie_ticket.service.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {

    private final ShowtimeService showtimeService;


    @PostMapping
    public ResponseEntity<Showtime> createShowtime(@RequestBody Showtime showtime) {
        Showtime createdShowtime = showtimeService.createShowtime(showtime);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShowtime);
    }


    @GetMapping("/upcoming")
    public ResponseEntity<List<Showtime>> getUpcomingShowtimes() {
        List<Showtime> showtimes = showtimeService.getUpcomingShowtimes();
        return ResponseEntity.ok(showtimes);
    }


    @GetMapping("/{showtimeId}")
    public ResponseEntity<Showtime> getShowtimeById(@PathVariable Long showtimeId) {
        Showtime showtime = showtimeService.getShowtimeById(showtimeId);
        return ResponseEntity.ok(showtime);
    }


    @PutMapping("/{showtimeId}")
    public ResponseEntity<Showtime> updateShowtime(
            @PathVariable Long showtimeId,
            @RequestBody Showtime showtimeDetails) {
        Showtime updatedShowtime = showtimeService.updateShowtime(showtimeId, showtimeDetails);
        return ResponseEntity.ok(updatedShowtime);
    }

    @GetMapping
    public ResponseEntity<List<Showtime>> getAllShowtimes() {
        List<Showtime> showtimes = showtimeService.getAllShowtimes(); // ✅ Dùng method service
        return ResponseEntity.ok(showtimes);
    }


    @DeleteMapping("/{showtimeId}")
    public ResponseEntity<Void> deleteShowtime(@PathVariable Long showtimeId) {
        showtimeService.deleteShowtime(showtimeId); // ✅ Dùng method service
        return ResponseEntity.ok().build();
    }
}
