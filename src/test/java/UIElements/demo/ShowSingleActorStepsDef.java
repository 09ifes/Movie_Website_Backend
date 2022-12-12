package UIElements.demo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.ResourceAccessException;

@ScenarioScope
public class ShowSingleActorStepsDef {
    @Autowired
    ActorRepository actorRepo;

    int actorID;
    Actor actor;

    @Given("an actor exists with id {int}")
    public void an_actor_exists_with_id(int id){
        actorID = id;
        actorRepo.findById(actorID).orElseThrow(() -> new ResourceAccessException("Actor ID does not exist" + actorID));
    }

    @When("i request an actor's details")
    public void i_request_an_actors_details(){
        actor = actorRepo.findById(actorID).orElseThrow(() -> new ResourceAccessException("Actor ID does not exist" + actorID));
        Assertions.assertNotEquals(null, actor, "Actor was not found");
    }

    @Then("the webpage should show the actor's firstname {string} and lastname {string}")
    public void the_webpage_should_show_the_actor_s_firstname_and_lastname(String firstname, String lastname){
        String correctDetails = firstname + " " + lastname;
        String testDetails = actor.getFirstName() + " " + actor.getLastName();
        Assertions.assertEquals(correctDetails, testDetails, "Details for actors are mismatched");

    }
}
