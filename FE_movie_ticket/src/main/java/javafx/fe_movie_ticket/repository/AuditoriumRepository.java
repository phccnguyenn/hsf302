package javafx.fe_movie_ticket.repository;

import javafx.fe_movie_ticket.entity.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {

    List<Auditorium> findByIsActiveTrue();

    // Comment out vì Auditorium không có field 'location'
    // List<Auditorium> findByLocationContainingIgnoreCaseAndIsActiveTrue(String location);

    boolean existsByNameAndIsActiveTrue(String name);

    List<Auditorium> findByNameContainingIgnoreCaseAndIsActiveTrue(String name);
}
