package javafx.fe_movie_ticket.controller;

import javafx.fe_movie_ticket.entity.dto.CinemaPostDto;
import javafx.fe_movie_ticket.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CinemaController {

    private final CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    public void addNewCinema(CinemaPostDto cinemaPostDto) {
        cinemaService.addNewCinema(cinemaPostDto);
    }
}
