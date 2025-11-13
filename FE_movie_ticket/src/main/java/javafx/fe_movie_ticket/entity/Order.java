package javafx.fe_movie_ticket.entity;

import jakarta.persistence.*;
import javafx.fe_movie_ticket.entity.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_code", unique = true, nullable = false, updatable = false)
    private String ticketCode; // Mã vé duy nhất, không trùng đâu yên tâm nha anh

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private User staff;

    @ManyToOne
    @JoinColumn(name = "showtime_id")
    private Showtime showtime;

    @ManyToMany
    @JoinTable(
            name = "order_refreshments",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "refreshment_id")
    )
    private List<Refreshment> refreshments;

    @Column(name = "total_price", precision = 15, scale = 0)
    private BigDecimal totalPrice;

    @Column(name = "order_date")
    private LocalDateTime orderDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @PrePersist
    public void generateTicketCode() {
        if (this.ticketCode == null || this.ticketCode.isEmpty()) {
            this.ticketCode = UUID.randomUUID().toString();
        }
    }
}
