package javafx.fe_movie_ticket.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private String id;
    private String movieTitle;
    private String moviePoster;
    private String cinemaName;
    private String cinemaAddress;
    private String theaterName;
    private LocalDateTime showtime;
    private String seats;
    private String format;
    private Double totalAmount;
    private Double ticketPrice;
    private Double serviceFee;
    private Integer quantity;
    private LocalDateTime bookingDate;
    private String status; // confirmed, used, cancelled
    
    // Constructors
    public Ticket() {}
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getMovieTitle() {
        return movieTitle;
    }
    
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
    
    public String getMoviePoster() {
        return moviePoster;
    }
    
    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }
    
    public String getCinemaName() {
        return cinemaName;
    }
    
    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }
    
    public String getCinemaAddress() {
        return cinemaAddress;
    }
    
    public void setCinemaAddress(String cinemaAddress) {
        this.cinemaAddress = cinemaAddress;
    }
    
    public String getTheaterName() {
        return theaterName;
    }
    
    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }
    
    public LocalDateTime getShowtime() {
        return showtime;
    }
    
    public void setShowtime(LocalDateTime showtime) {
        this.showtime = showtime;
    }
    
    public String getSeats() {
        return seats;
    }
    
    public void setSeats(String seats) {
        this.seats = seats;
    }
    
    public String getFormat() {
        return format;
    }
    
    public void setFormat(String format) {
        this.format = format;
    }
    
    public Double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public Double getTicketPrice() {
        return ticketPrice;
    }
    
    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    
    public Double getServiceFee() {
        return serviceFee;
    }
    
    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public LocalDateTime getBookingDate() {
        return bookingDate;
    }
    
    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    // Helper methods for formatting in templates
    public String getFormattedShowtime() {
        if (showtime == null) return "";
        return showtime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm"));
    }
    
    public String getFormattedShowDate() {
        if (showtime == null) return "";
        return showtime.format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy"));
    }
    
    public String getFormattedShowTime() {
        if (showtime == null) return "";
        return showtime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    
    public String getFormattedBookingDate() {
        if (bookingDate == null) return "";
        return bookingDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
    
    public String getFormattedTotalAmount() {
        if (totalAmount == null) return "0";
        return String.format("%,.0f VND", totalAmount);
    }
    
    public String getStatusBadgeClass() {
        switch (status != null ? status.toLowerCase() : "") {
            case "confirmed":
                return "status-confirmed";
            case "used":
                return "status-used";
            case "cancelled":
                return "status-cancelled";
            default:
                return "status-unknown";
        }
    }
    
    public String getStatusText() {
        switch (status != null ? status.toLowerCase() : "") {
            case "confirmed":
                return "Confirmed";
            case "used":
                return "Used";
            case "cancelled":
                return "Cancelled";
            default:
                return "Unknown";
        }
    }
}