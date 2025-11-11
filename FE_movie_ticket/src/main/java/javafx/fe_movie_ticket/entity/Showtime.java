package javafx.fe_movie_ticket.entity;

import jakarta.persistence.*;
import javafx.fe_movie_ticket.entity.enumeration.Language;
import javafx.fe_movie_ticket.entity.enumeration.ShowtimeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "showtimes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showtimeId;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="auditorium_id")
    private Auditorium auditorium;

    @Column(nullable=false)
    private Long movieId;

    @Column(name="starts_at", nullable=false)
    private LocalDateTime startsAt;

    @Column(name="ends_at", nullable=false)
    private LocalDateTime endsAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=10)
    private Language language;

    @Enumerated(EnumType.STRING) @Column(nullable=false, length=12)
    private ShowtimeStatus status ;

}
