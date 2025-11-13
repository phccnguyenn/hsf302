package javafx.fe_movie_ticket.repository;


import javafx.fe_movie_ticket.entity.Auditorium;
import javafx.fe_movie_ticket.entity.Seat;
import javafx.fe_movie_ticket.entity.enumeration.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {

    List<Seat> findBySeatTypeAndIsActiveTrue(SeatType seatType);
    List<Seat> findBySeatType(SeatType seatType);
    List<Seat> findByAuditoriumAuditoriumIdAndIsActive(Long auditoriumId, Boolean isActive);
    List<Seat> findByRowLabelAndSeatNumber(String rowLabel, String seatNumber);
    // Sửa từ findByAuditoriumId thành findByAuditoriumAuditoriumId để khớp với entity
    List<Seat> findByAuditoriumAuditoriumId(Long auditoriumId);
    long countByAuditoriumAuditoriumId(Long auditoriumId);

    List<Seat> findByRowLabelAndAuditoriumAuditoriumIdAndIsActiveTrue(String rowLabel, Long auditoriumId);
    List<Seat> findByAuditoriumAuditoriumIdAndIsActiveTrue(Long auditoriumId);
    boolean existsByAuditoriumAuditoriumIdAndRowLabelAndSeatNumberAndIsActiveTrue(
            Long auditoriumId, String rowLabel, String seatNumber);
}
