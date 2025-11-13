package javafx.fe_movie_ticket.entity;

import jakarta.persistence.*;
import javafx.fe_movie_ticket.entity.enumeration.Category;

import java.text.NumberFormat;
import java.util.Locale;

@Entity
@Table(name = "refreshments")
public class Refreshment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category; // "COMBO", "POPCORN", "DRINK", "SNACK"
    
    private String imageUrl;

    private String description;
    
    // Constructors
    public Refreshment() {}
    
    public Refreshment(String name, String description, double price, Category category, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
    }
    
    public Refreshment(Long id, String name, String description, double price, Category category, String imageUrl) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    // Utility method to format price
    public String getFormattedPrice() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return formatter.format(price);
    }
}