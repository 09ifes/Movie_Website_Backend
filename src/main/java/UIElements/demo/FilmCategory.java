package UIElements.demo;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.*;

@Entity
@Table(name="film_category")
public class FilmCategory {

    @Id
    @Column(name="film_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    private int filmID;

    @Column(name="category_id")
    private int categoryID;

    public FilmCategory(){

    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
