package javafx.fe_movie_ticket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="auditoriums")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Auditorium {
//    private Long id;
//    private String name; // e.g., "Screen 5", "Gold Class 2"
//    private String format; // e.g., "2D Phụ Đề Anh", "IMAX"
//
//    public Auditorium(Long id, String name, String format) {
//        this.id = id;
//        this.name = name;
//        this.format = format;
//    }
//
//
//
//    // Getters
//    public Long getId() { return id; }
//    public String getName() { return name; }
//    public String getFormat() { return format; }
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long auditoriumId;

    @ManyToOne(optional=false, fetch= FetchType.LAZY)
    @JoinColumn(name="cinema_id")
    private Cinema cinema;

    @Column(nullable=false, length=120)
    private String name;

    @Column(nullable=false)
    private Integer seatCapacity;

    @Column(nullable=false)
    private boolean isActive = true;

    @OneToMany(mappedBy="auditorium", cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<Seat> seats = new HashSet<>();

    @OneToMany(mappedBy="auditorium", cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<Showtime> showtimes = new HashSet<>();
}