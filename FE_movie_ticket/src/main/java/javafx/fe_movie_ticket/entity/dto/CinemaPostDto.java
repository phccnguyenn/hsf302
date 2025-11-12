package javafx.fe_movie_ticket.entity.dto;

import jakarta.persistence.*;
import javafx.fe_movie_ticket.entity.Auditorium;

import java.util.HashSet;
import java.util.Set;

public class CinemaPostDto {

    private String name;

    private String address;

    private boolean isActive = true;

    public CinemaPostDto() {
    }

    public CinemaPostDto(String name, String address, boolean isActive) {
        this.name = name;
        this.address = address;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
