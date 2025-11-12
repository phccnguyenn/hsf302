package javafx.fe_movie_ticket.service;

import javafx.fe_movie_ticket.entity.Cinema;
import javafx.fe_movie_ticket.entity.dto.CinemaPostDto;
import javafx.fe_movie_ticket.repository.CinemaRepository;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public Cinema addNewCinema(CinemaPostDto cinema) {
        Cinema newCinema = new Cinema();
        newCinema.setName(cinema.getName());
        newCinema.setAddress(cinema.getAddress());
        newCinema.setActive(cinema.isActive());

        return cinemaRepository.save(newCinema);
    }
}
