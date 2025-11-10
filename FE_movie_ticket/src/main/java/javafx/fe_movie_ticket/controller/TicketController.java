package javafx.fe_movie_ticket.controller;

import javafx.fe_movie_ticket.entity.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TicketController {

    @GetMapping("/tickets")
    public String myTickets(Model model) {
        // Mock data for user tickets
        List<Ticket> upcomingTickets = createMockUpcomingTickets();
        List<Ticket> pastTickets = createMockPastTickets();
        
        model.addAttribute("upcomingTickets", upcomingTickets);
        model.addAttribute("pastTickets", pastTickets);
        model.addAttribute("hasUpcomingTickets", !upcomingTickets.isEmpty());
        model.addAttribute("hasPastTickets", !pastTickets.isEmpty());
        
        return "user/my-tickets";
    }
    
    @GetMapping("/ticket/{ticketId}")
    public String ticketDetail(@PathVariable String ticketId, Model model) {
        // Mock data for ticket detail
        Ticket ticket = createMockTicketDetail(ticketId);
        model.addAttribute("ticket", ticket);
        return "user/ticket-detail";
    }

    private List<Ticket> createMockUpcomingTickets() {
        List<Ticket> tickets = new ArrayList<>();
        
        // Ticket 1
        Ticket ticket1 = new Ticket();
        ticket1.setId("TK001");
        ticket1.setMovieTitle("Spider-Man: No Way Home");
        ticket1.setMoviePoster("https://via.placeholder.com/300x450.png/E50914/FFFFFF?text=SPIDER-MAN");
        ticket1.setCinemaName("CGV Vincom Center");
        ticket1.setTheaterName("Screen 1");
        ticket1.setShowtime(LocalDateTime.now().plusDays(2).withHour(19).withMinute(30));
        ticket1.setSeats("F7, F8");
        ticket1.setFormat("2D");
        ticket1.setTotalAmount(240000.0);
        ticket1.setBookingDate(LocalDateTime.now().minusDays(1));
        ticket1.setStatus("confirmed");
        tickets.add(ticket1);
        
        // Ticket 2
        Ticket ticket2 = new Ticket();
        ticket2.setId("TK002");
        ticket2.setMovieTitle("Dune: Part Two");
        ticket2.setMoviePoster("https://via.placeholder.com/300x450.png/1F1F1F/FFFFFF?text=DUNE");
        ticket2.setCinemaName("CGV Aeon Mall");
        ticket2.setTheaterName("Screen 3");
        ticket2.setShowtime(LocalDateTime.now().plusDays(5).withHour(21).withMinute(0));
        ticket2.setSeats("G5, G6");
        ticket2.setFormat("IMAX");
        ticket2.setTotalAmount(320000.0);
        ticket2.setBookingDate(LocalDateTime.now().minusHours(3));
        ticket2.setStatus("confirmed");
        tickets.add(ticket2);
        
        // Ticket 3
        Ticket ticket3 = new Ticket();
        ticket3.setId("TK003");
        ticket3.setMovieTitle("Fast X");
        ticket3.setMoviePoster("https://via.placeholder.com/300x450.png/333333/FFFFFF?text=FAST+X");
        ticket3.setCinemaName("CGV Landmark 81");
        ticket3.setTheaterName("Screen 7");
        ticket3.setShowtime(LocalDateTime.now().plusDays(1).withHour(15).withMinute(45));
        ticket3.setSeats("H3");
        ticket3.setFormat("4DX");
        ticket3.setTotalAmount(450000.0);
        ticket3.setBookingDate(LocalDateTime.now().minusDays(2));
        ticket3.setStatus("confirmed");
        tickets.add(ticket3);
        
        return tickets;
    }

    private List<Ticket> createMockPastTickets() {
        List<Ticket> tickets = new ArrayList<>();
        
        // Past Ticket 1
        Ticket ticket1 = new Ticket();
        ticket1.setId("TK004");
        ticket1.setMovieTitle("Avatar: The Way of Water");
        ticket1.setMoviePoster("https://via.placeholder.com/300x450.png/0077BE/FFFFFF?text=AVATAR");
        ticket1.setCinemaName("CGV Vincom Center");
        ticket1.setTheaterName("Screen 2");
        ticket1.setShowtime(LocalDateTime.now().minusDays(10).withHour(20).withMinute(15));
        ticket1.setSeats("E5, E6");
        ticket1.setFormat("3D");
        ticket1.setTotalAmount(280000.0);
        ticket1.setBookingDate(LocalDateTime.now().minusDays(12));
        ticket1.setStatus("used");
        tickets.add(ticket1);
        
        // Past Ticket 2
        Ticket ticket2 = new Ticket();
        ticket2.setId("TK005");
        ticket2.setMovieTitle("Top Gun: Maverick");
        ticket2.setMoviePoster("https://via.placeholder.com/300x450.png/FF6B35/FFFFFF?text=TOP+GUN");
        ticket2.setCinemaName("CGV Saigon Center");
        ticket2.setTheaterName("Screen 5");
        ticket2.setShowtime(LocalDateTime.now().minusDays(25).withHour(18).withMinute(30));
        ticket2.setSeats("D4, D5");
        ticket2.setFormat("2D");
        ticket2.setTotalAmount(220000.0);
        ticket2.setBookingDate(LocalDateTime.now().minusDays(27));
        ticket2.setStatus("used");
        tickets.add(ticket2);
        
        return tickets;
    }

    private Ticket createMockTicketDetail(String ticketId) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketId);
        ticket.setMovieTitle("Spider-Man: No Way Home");
        ticket.setMoviePoster("https://via.placeholder.com/300x450.png/E50914/FFFFFF?text=SPIDER-MAN");
        ticket.setCinemaName("CGV Vincom Center");
        ticket.setCinemaAddress("72 Le Thanh Ton, District 1, Ho Chi Minh City");
        ticket.setTheaterName("Screen 1");
        ticket.setShowtime(LocalDateTime.now().plusDays(2).withHour(19).withMinute(30));
        ticket.setSeats("F7, F8");
        ticket.setFormat("2D");
        ticket.setTotalAmount(240000.0);
        ticket.setBookingDate(LocalDateTime.now().minusDays(1));
        ticket.setStatus("confirmed");
        ticket.setTicketPrice(110000.0);
        ticket.setServiceFee(20000.0);
        ticket.setQuantity(2);
        return ticket;
    }
}