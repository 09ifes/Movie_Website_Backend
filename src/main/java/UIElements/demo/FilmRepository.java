package UIElements.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Integer> {

    @Query(value = "SELECT film.film_id, film.title, film.description, film.release_year, film.customer_rating," +
            " category.name, film.rating, film.img_url, film.video_url \n" +
            "FROM film\n" +
            "INNER JOIN film_category\n" +
            "ON film.film_id = film_category.film_id\n" +
            "INNER JOIN category\n" +
            "ON film_category.category_id = category.category_id", nativeQuery = true)
    ArrayList<FilmDetails> findAllFilms();

    @Query(value = "SELECT film.film_id, film.title, film.description, film.release_year, film.customer_rating," +
            " category.name, film.rating, film.img_url, film.video_url \n" +
            "FROM film\n" +
            "INNER JOIN film_category\n" +
            "ON film.film_id = film_category.film_id\n" +
            "INNER JOIN category\n" +
            "ON film_category.category_id = category.category_id\n" +
            "ORDER BY film.customer_rating DESC", nativeQuery = true)
    ArrayList<FilmDetails> mostPopular();

    @Query(value = "SELECT film.film_id, film.title, film.description, film.release_year, film.customer_rating," +
            " category.name, film.rating, film.img_url, film.video_url \n" +
            "FROM film\n" +
            "INNER JOIN film_category\n" +
            "ON film.film_id = film_category.film_id\n" +
            "INNER JOIN category\n" +
            "ON film_category.category_id = category.category_id\n" +
            "ORDER BY film.release_year DESC", nativeQuery = true)
    ArrayList<FilmDetails> mostRecent();

    @Query(value = "SELECT film.film_id, film.title, film.description, film.release_year, film.customer_rating," +
            " category.name, film.rating, film.img_url, film.video_url \n" +
            "FROM film\n" +
            "INNER JOIN film_category\n" +
            "ON film.film_id = film_category.film_id\n" +
            "INNER JOIN category\n" +
            "ON film_category.category_id = category.category_id\n" +
            "WHERE title LIKE :title ", nativeQuery = true)
    ArrayList<FilmDetails> searchFilm(@Param("title") String title);

    @Query(value = "SELECT film.film_id, film.title, film.description, film.release_year, film.customer_rating," +
            " category.name, film.rating, film.img_url, film.video_url \n" +
            "FROM film\n" +
            "INNER JOIN film_category\n" +
            "ON film.film_id = film_category.film_id\n" +
            "INNER JOIN category\n" +
            "ON film_category.category_id = category.category_id\n" +
            "WHERE category.name = :category ", nativeQuery = true)
    ArrayList<FilmDetails> searchCategory(@Param("category") String category);

    @Query(value = "SELECT actor.first_name, actor.last_name, film.title\n" +
            "FROM actor\n" +
            "INNER JOIN film_actor\n" +
            "ON actor.actor_id = film_actor.actor_id\n" +
            "INNER JOIN film\n" +
            "ON film_actor.film_id = film.film_id\n" +
            "WHERE film_actor.film_id = :filmID", nativeQuery = true)
    ArrayList<FilmActorIntf> film_all_actors(@Param("filmID") int filmID);

    @Query(value = "SELECT film.film_id, film.title, film.description, film.release_year, film.customer_rating," +
            " category.name, film.rating, film.img_url, film.video_url \n" +
            "FROM film\n" +
            "INNER JOIN film_category\n" +
            "ON film.film_id = film_category.film_id\n" +
            "INNER JOIN category\n" +
            "ON film_category.category_id = category.category_id\n" +
            "WHERE film.film_id = :filmID", nativeQuery = true)
    ArrayList<FilmDetails> view_film(@Param("filmID") int filmID);

    // checks actors in a film, checks all the films the actors has been in,
    // filters out films that have same category as the specified film
    @Query(value = "SELECT film.film_id, film.title, film.description, film.release_year, film.customer_rating, " +
            " category.name, film.rating, film.img_url, film.video_url \n" +
            "FROM film_actor\n " +
            "INNER JOIN film\n " +
            "ON film_actor.film_id = film.film_id\n " +
            "INNER JOIN film_category\n " +
            "ON film.film_id = film_category.film_id\n " +
            "INNER JOIN category\n " +
            "ON film_category.category_id = category.category_id\n " +
            "WHERE actor_id IN\n " +
            "(SELECT actor_id FROM sakila.film_actor\n " +
            "WHERE film_id = :filmID)\n " +
            "AND category.name = :catName \n " +
            "GROUP BY film_id \n" +
            "ORDER BY film_id ASC \n" +
            "LIMIT 20;", nativeQuery = true)
    ArrayList<FilmDetails> similar_films(@Param("filmID") int filmID, @Param("catName") String catName);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO sakila.film (title, description, img_url, video_url) \n" +
            " VALUES(:title, :description, :img_url, :vid_url) ;", nativeQuery = true)
    void add_film(@Param("title") String title, @Param("description") String description,
                  @Param("img_url") String img_url, @Param("vid_url") String vid_url);

    @Transactional
    @Modifying
    @Query(value = "UPDATE sakila.film\n " +
            "SET title = :title, description = :description, img_url = :img_url, video_url = :vid_url " +
            "WHERE film_id = :filmID ; ", nativeQuery = true )
    void edit_film(@Param("title") String title, @Param("description") String description,
                   @Param("img_url") String img_url, @Param("vid_url") String vid_url, @Param("filmID") int filmID);









}







