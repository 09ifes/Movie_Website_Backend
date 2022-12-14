package UIElements.demo;


import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name="actor")
public class Actor implements Serializable {

    @Id
    @Column(name="actor_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY )
    private int actorID;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;


    public Actor(){

    }

    public int getActorID() {
        return actorID;
    }

    public void setActorID(int actorID) {
        this.actorID = actorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }




}
