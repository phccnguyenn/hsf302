package javafx.fe_movie_ticket.entity;

public class Showtime {
    private Long id;
    private String time; // e.g., "04:30 PM"
    private String format; // e.g., "2D"

    public Showtime() {
    }

    public Showtime(Long id, String time, String format) {
        this.id = id;
        this.time = time;
        this.format = format;
    }

    public Showtime(String time, String format) {
        this.time = time;
        this.format = format;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
