package UIElements.demo;


import jakarta.persistence.*;



@Entity
@Table(name="film")
public class Film {
    @Id
    @Column(name="film_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY )
    private int filmID;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;






    public Film(){

    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
