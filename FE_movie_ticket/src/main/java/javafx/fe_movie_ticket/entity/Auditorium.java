package javafx.fe_movie_ticket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="auditoriums")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Auditorium {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long auditoriumId;

            @ManyToOne(optional=false, fetch= FetchType.LAZY)
            @JoinColumn(name="cinema_id")
            private Cinema cinema;

            @Column(nullable=false, length=120)
            private String name;

            @Column(nullable=false)
            private boolean isActive = true;

            @OneToMany(mappedBy="auditorium", cascade=CascadeType.ALL, orphanRemoval=true)
            private Set<Seat> seats = new HashSet<>();

            @OneToMany(mappedBy="auditorium", cascade=CascadeType.ALL, orphanRemoval=true)
            private Set<Showtime> showtimes = new HashSet<>();

            public Integer getAvailableSeatCount() {
                return (int) seats.stream()
                        .filter(Seat::isActive)
                        .count();
            }
            public boolean hasEnoughSeats() {
                long activeSeatCount = seats.stream()
                        .filter(Seat::isActive)
                        .count();
                return activeSeatCount > 0;
            }


            public Map<String, Long> getSeatCountByType() {
                return seats.stream()
                        .filter(Seat::isActive)
                        .collect(Collectors.groupingBy(
                                seat -> seat.getSeatType().name(),
                                Collectors.counting()
                        ));
            }
            public boolean isOperational() {
                return isActive && hasEnoughSeats();
            }
}