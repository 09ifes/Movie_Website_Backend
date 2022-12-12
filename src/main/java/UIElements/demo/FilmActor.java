package UIElements.demo;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.*;

import java.io.Serializable;

@IdClass(FilmActorID.class)
@Entity
@Table(name="film_actor")
public class FilmActor {

    @Id
    @ManyToOne
    @JoinColumn(name="actor_id", referencedColumnName="actor_id")
    private Actor actorID;

    @Id
    @ManyToOne
    @JoinColumn(name="film_id", referencedColumnName="film_id")
    private Film filmID;


    public FilmActor(){

    }


    public Actor getActor() {
        return actorID;
    }

    public void setActor(Actor actor) {
        this.actorID = actor;
    }

    public Actor getActorID() {
        return actorID;
    }

    public void setActorID(Actor actorID) {
        this.actorID = actorID;
    }

    public Film getFilmID() {
        return filmID;
    }

    public void setFilmID(Film filmID) {
        this.filmID = filmID;
    }
}
