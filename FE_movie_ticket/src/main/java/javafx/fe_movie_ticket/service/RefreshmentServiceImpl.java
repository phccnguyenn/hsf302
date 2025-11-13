package javafx.fe_movie_ticket.service;

import javafx.fe_movie_ticket.entity.Refreshment;
import javafx.fe_movie_ticket.entity.enumeration.Category;
import javafx.fe_movie_ticket.repository.RefreshmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefreshmentServiceImpl implements RefreshmentService {

    private final RefreshmentRepository repository;

    @Override
    public Refreshment create(Refreshment refreshment) {
        return repository.save(refreshment);
    }

    @Override
    public Refreshment getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Refreshment not found"));
    }

    @Override
    public List<Refreshment> getAll() {
        return repository.findAll();
    }

    @Override
    public Refreshment update(Long id, Refreshment refreshment) {
        Refreshment existing = getById(id);
        existing.setName(refreshment.getName());
        existing.setPrice(refreshment.getPrice());
        existing.setCategory(refreshment.getCategory());
        existing.setImageUrl(refreshment.getImageUrl());
        existing.setDescription(refreshment.getDescription());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Refreshment> getByCategory(Category category) {
        return repository.findByCategory(category);
    }
}
