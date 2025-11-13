package javafx.fe_movie_ticket.repository;

import javafx.fe_movie_ticket.entity.Order;
import javafx.fe_movie_ticket.entity.enumeration.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByStatus(OrderStatus status);
    Optional<Order> findByTicketCode(String ticketCode);
}

