package javafx.fe_movie_ticket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


public class Movie {

    private Long id;
    private String title;
    private String genre;
    private int durationMin;
    private String posterUrl;
    private String status;
    private String rating; // e.g., "PG-13"
    private String synopsis;
    private String trailerUrl; // A YouTube embed link

    public Movie() {
    }

    public Movie(String title, String genre, int durationMin, String posterUrl, String status) {
        this.title = title;
        this.genre = genre;
        this.durationMin = durationMin;
        this.posterUrl = posterUrl;
        this.status = status;
    }

    public Movie(Long id, String title, String genre, int durationMin, String posterUrl, String status) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.durationMin = durationMin;
        this.posterUrl = posterUrl;
        this.status = status;
    }

    public Movie(Long id, String title, String genre, int durationMin, String posterUrl, String status, String rating, String synopsis, String trailerUrl) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.durationMin = durationMin;
        this.posterUrl = posterUrl;
        this.status = status;
        this.rating = rating;
        this.synopsis = synopsis;
        this.trailerUrl = trailerUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }
}
