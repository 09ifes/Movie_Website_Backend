package UIElements.demo;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import jakarta.persistence.*;

@Entity
@Table(name="category")
public class Category {

    @Id
    @Column(name="category_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    private int categoryID;

    @Column(name="name")
    private String categoryName;

    public Category(){

    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
