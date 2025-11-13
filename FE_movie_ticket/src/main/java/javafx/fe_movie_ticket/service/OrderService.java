package javafx.fe_movie_ticket.service;

import javafx.fe_movie_ticket.entity.Order;
import javafx.fe_movie_ticket.entity.enumeration.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(Long id);
    List<Order> getAllOrders();
    Order updateOrderStatus(Long id, OrderStatus status);
    void deleteOrder(Long id);
    Optional<Order> getByTicketCode(String ticketCode);
    List<Order> getOrdersByCustomer(Long customerId);
}

