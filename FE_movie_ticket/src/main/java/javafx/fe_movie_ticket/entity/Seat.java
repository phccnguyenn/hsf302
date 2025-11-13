package javafx.fe_movie_ticket.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import javafx.fe_movie_ticket.entity.enumeration.SeatType;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "seats")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;


    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @Column(nullable = false)
    private String seatNumber ;

    @Column(length = 5 ,nullable = false)
    private String rowLabel;

//    @Column(nullable = false)
//    private BigDecimal price;

    @Column(nullable = false)
    private boolean isActive ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorium_id", nullable = false)
    private Auditorium auditorium;
}
