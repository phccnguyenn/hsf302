package javafx.fe_movie_ticket.entity.dto;

import javafx.fe_movie_ticket.entity.Showtime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MoviePostDto {

    private String title;

    private Integer duration;

    private OffsetDateTime releaseDate;

    private Boolean isActive;

    private String movieUrl;

    private List<GenresPostDto> genresList;

    private List<Showtime> showtimeList;

}
