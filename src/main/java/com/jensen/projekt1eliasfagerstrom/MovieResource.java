package com.jensen.projekt1eliasfagerstrom;


import com.jensen.projekt1eliasfagerstrom.db.MovieRepository;
import com.jensen.projekt1eliasfagerstrom.model.Movie;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * Detta är klassen som integrear direkt med Postman för att sköta HTTP förgårningar.
 * Alla metoder är därför annoterade med @POST, @GET, @DELETE, @PUT
 */

// @Path bestämmer URLn som används för att interaktera med MovieResource
@Path("/movies")
public class MovieResource {

     // Injekterar klassen med MovieRepository så vi kan använda dess metoder och interaktera med databasen
    @Inject
    private MovieRepository movieRepository;

    /**
     * Metod för att hämta en movie.
     * Av typen @GET, en HTTP operation för att hämta data
     * @Produces innebär att datan kommer vara i json format
     * Använder findMovies metoden från MovieRepository för att hämta datan från databasen
     * @return En lista av movies från databasen
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getMovie(){
        return movieRepository.findMovies();
    }

    /**
     * Metod för att lägga till en movie i databasen
     * Av typen @POST, en HTTP operation för att lägga till data
     * @Consumer innebär att den tar in data av typen json
     * @Produces innebär att den kommer returnera data av typen TEXT_PLAIN vilket innebär vanlig text
     * Använder createMovie metoden från MovieRepository för att lägga till data till databasen.
     * @param movie
     * @return returnerar en HTTP statuskod (201) samt ett medelande som säger "Movie Created"
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addMovie(Movie movie){
        movieRepository.createMovie(movie);
        return Response.status(Response.Status.CREATED)
                .entity("Movie Created")
                .build();
    }

    /**
     * Metod för att hämta en spcifik movie utifrån id
     * Av typen @GET, en HTTP operation för att hämta data
     * @Path("/{id}) bestämmer att urln kommer vara IDt som sätts i PathParam
     * Använder metoden findById från MovieRepository för att söka i databasen baserat på Id
     * @param id
     * @return Om movie på det angivna IDt är null, returneras HTTP status kod 404, och medelandet "Movie Not Found.
     * Om movie hittas, returneras koden 202 och movien på det specifika IDt
     */
    @GET
    @Path("/{id}")
    public Response getMovieById(@PathParam("id") long id){
        Movie movie = movieRepository.findById(id);
        if(movie == null){
            return Response.status(Response.Status.NOT_FOUND).entity("Movie Not Found").build();
        }
        return Response.ok(movie).build();
    }

    /**
     * Metod för att ta bort en movie
     * Av typen @DELETE, en HTTP operation för att ta bort data
     * @Path("/{id}) bestämmer att urln kommer vara IDt som sätts i PathParam
     * Börjar med att kolla en existingMovie i databasen genom att använda findById metoden från MovieRepository
     * Om existingMovie inte är null, används delete metoden från MovieRepository för att ta bort movien från databasen
     * @param id
     * @return Om movie = null returneras HTTP koden 404 och texten Movie Not Found, om den finns returneras koden 202 och Movie deleted
     */
    @DELETE
    @Path("/{id}")
    public Response deleteMovieById(@PathParam("id") long id){
        Movie existingMovie = movieRepository.findById(id);
        if(existingMovie == null){
            return Response.status(Response.Status.NOT_FOUND).entity("Movie Not Found").build();
        }
        movieRepository.delete(id);
        return Response.ok("Movie deleted").build();
    }

    /**
     * Metodd för att updatera en movie
     * Av typen @PUT, en HTTP operation för att updatera existerande data
     * @Consumes innebär att den tar in data av typen json
     * Börjar med att kolla en existingMovie i databasen genom att använda findById metoden från MovieRepository
     * Om existingMovie inte är null, används set metoderna från movie klassen för att ändra den existerande movien
     * Set metoderna använder den updaterade movien som parameter för att ändra attributet.
     * Sist kallas update metoden från movieRepository med existingMovie som parameter för att uppdatera
     * @param id
     * @param updatedMovie
     * @return Om existingMovie = null, returneras koden 404 och texten Movie Not Found. Som existingMovie inte är null, returneras koden 202 Movie updated successfully
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateMovie(@PathParam("id") long id, Movie updatedMovie){
        Movie existingMovie = movieRepository.findById(id);
        if(existingMovie == null){
            return Response.status(Response.Status.NOT_FOUND).entity("Movie Not Found").build();
        }
        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setGenre(updatedMovie.getGenre());
        existingMovie.setRelease(updatedMovie.getRelease());
        existingMovie.setDescription(updatedMovie.getDescription());
        existingMovie.setDirector(updatedMovie.getDirector());
        movieRepository.update(existingMovie);
        return Response.ok("Movie updated successfully").build();

    }

}
