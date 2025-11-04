package javafx.fe_movie_ticket.entity;

public class Auditorium {
    private Long id;
    private String name; // e.g., "Screen 5", "Gold Class 2"
    private String format; // e.g., "2D Phụ Đề Anh", "IMAX"

    public Auditorium(Long id, String name, String format) {
        this.id = id;
        this.name = name;
        this.format = format;
    }



    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getFormat() { return format; }
}