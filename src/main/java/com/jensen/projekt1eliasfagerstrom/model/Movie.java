package com.jensen.projekt1eliasfagerstrom.model;
import jakarta.persistence.*;

/**
 * Denna metod definerar hur ett movie objekt i databasen ska se ut
 * Attributen skapar kolumnerna i databasen.
 */

@Entity
@Table(name = "app_movie")
public class Movie {

    /**
     * @ID och @GeneratedValue gör så att varje movie får ett ID genererat automatiskt av databasen, och att det är huvud identifieraren
     */
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "release_year", nullable = false)
    private int release;

    @Column(name = "description")
    private String description;

    @Column(name = "director", nullable = false)
    private String director;

    /**
     * Tom konstruktor
     */
    public Movie() {}

    /**
     * Konstruktor för movie med attributen
     * @param title
     * @param genre
     * @param release
     * @param description
     * @param director
     */
    public Movie(String title, String genre, int release, String description, String director) {
        this.title = title;
        this.genre = genre;
        this.release = release;
        this.description = description;
        this.director = director;
    }

    /**
     * Getter metod för id
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter metod för id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter metod för titel
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter metod för titel
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter metod för genre
     * @return genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Setter metod för genre
     * @param genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Getter metod för release year
     * @return release
     */
    public int getRelease() {
        return release;
    }

    /**
     * Setter metod för release year
     * @param release
     */
    public void setRelease(int release) {
        this.release = release;
    }

    /**
     * Getter metod för description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter metod för description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter metod för director
     * @return director
     */
    public String getDirector() {
        return director;
    }

    /**
     * Setter metod för director
     * @param director
     */
    public void setDirector(String director) {
        this.director = director;
    }
}
