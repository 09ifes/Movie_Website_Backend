import UIElements.demo.Film;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class FilmTests {

    @Test
    public void testGetSetFilmID(){
        Random rand_x = new Random();
        int x = rand_x.nextInt(1000);
        Film film = new Film();
        film.setFilmID(x);
        Assertions.assertEquals(x, film.getFilmID(), "Film ID should be: " + x);
    }

    @Test
    public void testGetSetTitle(){
        String title = "title";
        Film film = new Film();
        film.setTitle(title);
        Assertions.assertEquals(title, film.getTitle(), "Title should be: " + title);
    }

    @Test
    public void testGetSetDescription(){
        String description = "description";
        Film film = new Film();
        film.setDescription(description);
        Assertions.assertEquals(description, film.getDescription(), "Description should be: " + description);
    }





}
