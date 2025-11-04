package javafx.fe_movie_ticket.entity;

public class Showtime {
    private Long id;
    private String time; // e.g., "04:30 PM"
    private Auditorium auditorium; // CHANGE: Link to the Auditorium object


    public Showtime() {
    }

    public Showtime(Long id, String time, Auditorium auditorium) {
        this.id = id;
        this.time = time;
        this.auditorium = auditorium;
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

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }
}
