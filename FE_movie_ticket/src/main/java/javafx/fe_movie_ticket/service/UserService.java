package javafx.fe_movie_ticket.service;

import javafx.fe_movie_ticket.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
