package com.jensen.projekt1eliasfagerstrom.db;

import com.jensen.projekt1eliasfagerstrom.model.Movie;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

/**
 * Denna klass hanterar interaktion med databasen.
 * Använder EntityManager för att använda JPA operationer som interakterar med databasen
 */

// Innebär att en instans av klassen ska alltid vara på så länge som programet är på och tillgänglig
@ApplicationScoped
// Hanterar alla databas anrop som en transaktion
@Transactional
public class MovieRepository {
    // Injectar klassen med EntityManager.
    @PersistenceContext
    // Skapar upp våran EntityManager och gör att vi kan använda den
    private EntityManager entityManager;

    /**
     * Metod för att hämta filmerna i databasen.
     * @return Returnerar film objekt i databasen
     */
    public List<Movie> findMovies() {
        // Databasen hämtar alla rader från app_movies
        String sql = "SELECT * FROM app_movie";
        // Skickar en förfrågan till till databasen, och utgår från datan i movie klassen
        Query query = entityManager.createNativeQuery(sql, Movie.class);
        // Skickar tillbaka resultatet av förfrågan
        return query.getResultList();
    }

    /**
     * Metod för att skapa upp filmer i databasen
     * @param movie
     */
    public void createMovie(Movie movie) {
        // EntityManager använder JPA operationen persist för att lägga till en movie till databasen
        entityManager.persist(movie);
    }

    /**
     * Metod för att söka upp en specifik movie baserat på ID
     * @param id
     * @return Returnerar en movie av ett specifikt id från databasen
     */
    public Movie findById(long id) {
        return entityManager.find(Movie.class, id);
    }

    /**
     * Metod för att updatera en movie i databasen.
     * EntityManager använder JPA operationen merge för att updatera den existerande datan
     * @param movie
     */
    public void update(Movie movie) {
        entityManager.merge(movie);
    }

    /**
     * Metod för att ta bort en movie från databasen
     * Efter att kolla om movie existerar, använder metoden JPA operationen remove för att ta bort från databasen.
     * @param id
     */
    public void delete(Long id) {
        Movie movie = findById(id);
        if (movie != null) {
            entityManager.remove(movie);
        }
    }
}
