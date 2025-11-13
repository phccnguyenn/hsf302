package javafx.fe_movie_ticket.entity;

import jakarta.persistence.*;
import javafx.fe_movie_ticket.entity.enumeration.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name = "ticket_url")
    private String ticketUrl;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "ticket_price")
    private BigDecimal ticketPrice;

    @Column(name = "ticket_status")
    private TicketStatus ticketStatus;
}