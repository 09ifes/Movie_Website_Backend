import UIElements.demo.Actor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;


public class ActorTests {

    @Test
    public void getSetActorID(){
        Random rand_x = new Random();
        int x = rand_x.nextInt(1000);
        Actor actor = new Actor();
        actor.setActorID(x);
        Assertions.assertEquals(x, actor.getActorID(), "Actor ID should be: " + x);
    }

    @Test
    public void getSetFirstName(){
        String firstName = "firstName";
        Actor actor = new Actor();
        actor.setFirstName(firstName);
        Assertions.assertEquals(firstName, actor.getFirstName(), "Actor first name should be: " + firstName);
    }

    @Test
    public void getSetLastName(){
        String lastName = "lastName";
        Actor actor = new Actor();
        actor.setLastName(lastName);
        Assertions.assertEquals(lastName, actor.getLastName(), "Actor last name should be: " + lastName);
    }





}
