package UIElements.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Integer> {

    @Query(value = "SELECT film.title, film_category.category_id, category.name\n" +
            "FROM film\n" +
            "INNER JOIN film_category\n" +
            "ON film.film_id = film_category.film_id\n" +
            "INNER JOIN category\n" +
            "ON film_category.category_id = category.category_id", nativeQuery = true)
    ArrayList<FilmCatIntf> findAllFilms();

    @Query(value = "SELECT film.title, film_category.category_id, category.name\n" +
            "FROM film\n" +
            "INNER JOIN film_category\n" +
            "ON film.film_id = film_category.film_id\n" +
            "INNER JOIN category\n" +
            "ON film_category.category_id = category.category_id\n" +
            "WHERE category.name = :category ", nativeQuery = true)
    ArrayList<FilmCatIntf> searchCategory(@Param("category") String category);

    @Query(value = "SELECT actor.first_name, actor.last_name, film.title\n" +
            "FROM actor\n" +
            "INNER JOIN film_actor\n" +
            "ON actor.actor_id = film_actor.actor_id\n" +
            "INNER JOIN film\n" +
            "ON film_actor.film_id = film.film_id\n" +
            "WHERE film_actor.film_id = :filmID", nativeQuery = true)
    ArrayList<FilmActorIntf> film_all_actors(@Param("filmID") int filmID);

    @Query(value = "SELECT film.film_id, film.title, film.description, category.name\n" +
            "FROM film\n" +
            "INNER JOIN film_category\n" +
            "ON film.film_id = film_category.film_id\n" +
            "INNER JOIN category\n" +
            "ON film_category.category_id = category.category_id\n" +
            "WHERE film.film_id = :filmID", nativeQuery = true)
    ArrayList<FilmDetails> view_film(@Param("filmID") int filmID);

    // checks actors in a film, checks all the films the actors has been in,
    // filters out films that have same category as the specified film
    @Query(value = "SELECT film.film_id, film.title, film.description, category.name, film.release_year, film.rating, " +
            "film.customer_rating \n" +
            "FROM film_actor\n" +
            "INNER JOIN film\n" +
            "ON film_actor.film_id = film.film_id\n" +
            "INNER JOIN film_category\n" +
            "ON film.film_id = film_category.film_id\n" +
            "INNER JOIN category\n" +
            "ON film_category.category_id = category.category_id\n" +
            "WHERE actor_id IN\n" +
            "(SELECT actor_id FROM sakila.film_actor\n" +
            "WHERE film_id = :filmID)\n" +
            "AND category.name = \"Action\"\n" +
            "GROUP BY film_id; ", nativeQuery = true)
    ArrayList<FilmDetails> similar_films(@Param("filmID") int filmID);

}







