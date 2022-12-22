import UIElements.demo.Film;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class FilmTests {

    @Test
    public void testGETSetFilmID(){
        Random rand_x = new Random();
        int x = rand_x.nextInt(1000);
        Film film = new Film();
        film.setFilmID(x);
        Assertions.assertEquals(x, film.getFilmID(), "Film ID should be: " + x);
    }




}
