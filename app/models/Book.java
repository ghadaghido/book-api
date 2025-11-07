package models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    private String subtitle;

    @Column(name = "copyright_year", nullable = false)
    private int copyrightYear;

    @Column(nullable = false)
    private String status;

    public Book() {}

    public Book(String isbn, String title, String subtitle, int copyrightYear, String status) {
        this.isbn = isbn;
        this.title = title;
        this.subtitle = subtitle;
        this.copyrightYear = copyrightYear;
        this.status = status;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSubtitle() { return subtitle; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }

    public int getCopyrightYear() { return copyrightYear; }
    public void setCopyrightYear(int copyrightYear) { this.copyrightYear = copyrightYear; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
