package javafx.fe_movie_ticket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "genres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Genres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genres_id")
    private Long genresId;

//    @ManyToOne
//    @JoinColumn(name = "movie_id")
//    private Movie movie;

    @Column(name = "genres_name")
    private String genresName;

}
