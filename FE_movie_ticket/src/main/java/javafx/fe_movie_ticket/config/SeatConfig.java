package javafx.fe_movie_ticket.config;

import javafx.fe_movie_ticket.entity.Auditorium;
import javafx.fe_movie_ticket.entity.Seat;
import javafx.fe_movie_ticket.entity.enumeration.SeatType;
import javafx.fe_movie_ticket.repository.AuditoriumRepository;
import javafx.fe_movie_ticket.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

//@Configuration
@Component
public class SeatConfig implements CommandLineRunner {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Override
    public void run(String... args) throws Exception {
        // Kiểm tra xem đã có ghế chưa để tránh tạo trùng
        if (seatRepository.count() == 0) {
            // Tìm hoặc tạo auditorium trước
            Auditorium aud = auditoriumRepository.findById(1L)
                    .orElseGet(() -> {

                        Auditorium newAud = new Auditorium();
                        newAud.setAuditoriumId(1L);
                        newAud.setName("Auditorium 1");
                        newAud.setSeatCapacity(20);
                        newAud.setActive(true);

                        return auditoriumRepository.save(newAud);
                    });

            // Tạo ghế hàng A
            for (int i = 1; i <= 10; i++) {
                Seat seat = new Seat();
                seat.setSeatType(SeatType.VIP);
                seat.setRowLabel("A");
                seat.setSeatNumber(String.valueOf(i)); // Sửa thành String
                seat.setPrice(new BigDecimal("150000"));
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
                seat.setPrice(new BigDecimal("100000"));
                seat.setActive(true);
                seat.setAuditorium(aud);
                seatRepository.save(seat);
            }
        }
    }
}
