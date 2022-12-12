package UIElements.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FilmActorRepository extends JpaRepository<FilmActor, Integer>{
}
