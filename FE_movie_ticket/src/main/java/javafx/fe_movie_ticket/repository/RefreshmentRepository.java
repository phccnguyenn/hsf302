package javafx.fe_movie_ticket.repository;

import javafx.fe_movie_ticket.entity.Refreshment;
import javafx.fe_movie_ticket.entity.enumeration.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefreshmentRepository extends JpaRepository<Refreshment, Long> {
    List<Refreshment> findByCategory(Category category);
}
