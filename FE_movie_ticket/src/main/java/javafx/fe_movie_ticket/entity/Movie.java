package javafx.fe_movie_ticket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    private String title;

    private Integer duration;

    @Column(name = "release_date")
    private OffsetDateTime releaseDate;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "movie_url")
    private String movieUrl;

    @OneToMany(mappedBy = "movie")
    private List<Genres> genresList = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<Showtime> showtimeList = new ArrayList<>();

}
