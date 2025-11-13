package javafx.fe_movie_ticket.service;

import javafx.fe_movie_ticket.entity.Refreshment;
import javafx.fe_movie_ticket.entity.enumeration.Category;

import java.util.List;

public interface RefreshmentService {
    Refreshment create(Refreshment refreshment);
    Refreshment getById(Long id);
    List<Refreshment> getAll();
    Refreshment update(Long id, Refreshment refreshment);
    void delete(Long id);
    List<Refreshment> getByCategory(Category category);
}
