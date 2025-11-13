package javafx.fe_movie_ticket.config;

import javafx.fe_movie_ticket.entity.Auditorium;
import javafx.fe_movie_ticket.entity.Cinema;
import javafx.fe_movie_ticket.entity.Seat;
import javafx.fe_movie_ticket.entity.enumeration.SeatType;
import javafx.fe_movie_ticket.repository.AuditoriumRepository;
import javafx.fe_movie_ticket.repository.CinemaRepository;
import javafx.fe_movie_ticket.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



//@Configuration
@Component
public class SeatConfig implements CommandLineRunner {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public void run(String... args) throws Exception {
        // Kiểm tra xem đã có ghế chưa để tránh tạo trùng
        if (seatRepository.count() == 0) {
            // Tạo cinema trước (required cho auditorium)
            Cinema cinema = cinemaRepository.findAll().stream()
                    .findFirst()
                    .orElseGet(() -> {
                        Cinema newCinema = new Cinema();
                        newCinema.setName("CGV Vincom Center");
                        newCinema.setAddress("72 Le Thanh Ton, District 1, Ho Chi Minh City");
                        newCinema.setActive(true);
                        return cinemaRepository.save(newCinema);
                    });

            // Tạo auditorium mới (không set ID manual vì có @GeneratedValue)
            Auditorium aud = auditoriumRepository.findAll().stream()
                    .findFirst()
                    .orElseGet(() -> {
                        Auditorium newAud = new Auditorium();
                        newAud.setName("Auditorium 1");
                        newAud.setActive(true);
                        newAud.setCinema(cinema);  // Set required cinema
                        return auditoriumRepository.save(newAud);
                    });

            // Tạo ghế hàng A
            for (int i = 1; i <= 10; i++) {
                Seat seat = new Seat();
                seat.setSeatType(SeatType.VIP);
                seat.setRowLabel("A");
                seat.setSeatNumber(String.valueOf(i)); // Sửa thành String
                seat.setActive(true);
                seat.setAuditorium(aud);
                seatRepository.save(seat);
            }

            // Tạo ghế hàng B (NORMAL)
            for (int i = 1; i <= 10; i++) {
                Seat seat = new Seat();
                seat.setSeatType(SeatType.COUPLE);
                seat.setRowLabel("B");
                seat.setSeatNumber(String.valueOf(i));
                seat.setActive(true);
                seat.setAuditorium(aud);
                seatRepository.save(seat);
            }
        }
    }
}
