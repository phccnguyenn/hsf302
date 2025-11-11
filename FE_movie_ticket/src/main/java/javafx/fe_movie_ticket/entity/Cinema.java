package javafx.fe_movie_ticket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cinemas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cinemaId;

    @Column(nullable=false, length=120)
    private String name;

    @Column(length=255)
    private String address;

    @Column(nullable=false)

    private boolean isActive = true;

    @OneToMany(mappedBy="cinema", cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<Auditorium> auditoriums = new HashSet<>();
}