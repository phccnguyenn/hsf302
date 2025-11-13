package javafx.fe_movie_ticket.controller;

import javafx.fe_movie_ticket.entity.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.ArrayList;
import java.util.List;

@Controller
public class TicketController {

    @GetMapping("/tickets")
    public String myTickets(Model model) {
        // Mock data for user tickets - using simple empty lists for now
        List<Ticket> upcomingTickets = new ArrayList<>();
        List<Ticket> pastTickets = new ArrayList<>();
        
        model.addAttribute("upcomingTickets", upcomingTickets);
        model.addAttribute("pastTickets", pastTickets);
        model.addAttribute("hasUpcomingTickets", !upcomingTickets.isEmpty());
        model.addAttribute("hasPastTickets", !pastTickets.isEmpty());
        
        return "user/my-tickets";
    }
    
    @GetMapping("/ticket/{ticketId}")
    public String ticketDetail(@PathVariable String ticketId, Model model) {
        // Mock data for ticket detail - using basic ticket entity
        Ticket ticket = new Ticket();
        ticket.setTicketId(Long.valueOf(ticketId));
        model.addAttribute("ticket", ticket);
        return "user/ticket-detail";
    }


}