package javafx.fe_movie_ticket.repository;

import javafx.fe_movie_ticket.entity.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenresRepository extends JpaRepository<Genres, Long> {
}
