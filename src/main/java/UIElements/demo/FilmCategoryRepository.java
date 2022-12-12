package UIElements.demo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmCategoryRepository extends JpaRepository<FilmCategory, Integer> {
}
