package UIElements.demo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;


@ScenarioScope
public class ViewFilmStepsDef {
    @Autowired
    FilmRepository filmRepo;
    int filmID;
    ArrayList<FilmDetails> film;

    @Given("a film exists with the id {int}")
    public void a_film_exists_with_the_id(int id) {
        filmID = id;
        try {
            filmRepo.view_film(filmID).get(0);
        }
        catch (Exception e){
             throw new IndexOutOfBoundsException("Film ID doesn't exist");
        }

    }

    @When("i request a film's additional details")
    public void i_request_a_film_s_additional_details(){

    film = filmRepo.view_film(filmID);

    Assertions.assertNotEquals(null, film, "Film was not found");
    }

    @Then("the webpage should show the film's title {string} and category {string} and release year {int}")
    public void the_webpage_should_show_the_film_s_title_and_category_and_release_year(String title,
                                                                                       String category, int release_year){

        FilmDetails film_object = film.get(0);


        String correctDetails = title + ", " + category + ", " + release_year ;
        String testDetails = film_object.gettitle()+ ", " + film_object.getname() + ", " + film_object.getrelease_year() ;
        Assertions.assertEquals(correctDetails, testDetails, "Details for actors are mismatched");

    }
}
