package UIElements.demo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.ResourceAccessException;


@ScenarioScope
public class ShowSingleFilmStepsDef {
    @Autowired
    FilmRepository filmRepo;
    int filmID;
    Film film;
    @Given("a film exists with id {int}")
    public void a_film_exists_with_id(int id){
        filmID = id;
        filmRepo.findById(filmID).orElseThrow(() -> new ResourceAccessException("Film ID does not exist" + filmID));
    }

    @When("i request a film's details")
    public void i_request_a_film_s_details(){
        film = filmRepo.findById(filmID).orElseThrow(() -> new ResourceAccessException("Film ID does not exist" + filmID));
        Assertions.assertNotEquals(null, film, "Film was not found");
    }

    @Then("the webpage should show the film's title {string}")
    public void the_webpage_should_show_the_film_s_title(String title){
        String correctDetails = title;
        String testDetails = film.getTitle();
        Assertions.assertEquals(correctDetails, testDetails, "Details for actors are mismatched");

    }
}
