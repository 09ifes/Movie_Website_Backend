package UIElements.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;

@SpringBootApplication
@Controller
@RequestMapping("/home")
@CrossOrigin
public class DemoApplication {

	@Autowired
	private FilmRepository filmRepo;
	@Autowired
	private ActorRepository actorRepo;

	public DemoApplication(FilmRepository fr, ActorRepository ar){
		filmRepo = fr;
		actorRepo = ar;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	ArrayList allFilms = new ArrayList<>();
	ArrayList mostPopular = new ArrayList<>();
	ArrayList mostRecent = new ArrayList<>();


	//public @ResponseBody

	@GetMapping("/get_film/{filmID}")
	public @ResponseBody
	Film getFilmByID(@PathVariable("filmID") int filmID){
		Film film = filmRepo.findById(filmID).
				orElseThrow(() -> new ResourceAccessException("Film ID doesn't exist: " + filmID));
		return film;
	}

	@PutMapping("/edit_film/{filmID}")
	Film editFilmByID(@RequestBody Film newFilm, @PathVariable("filmID") int filmID){
		 Film film = filmRepo.findById(filmID).map(film1 -> {
			film1.setTitle(newFilm.getTitle());
			film1.setDescription(newFilm.getDescription());
			return filmRepo.save(film1);

		}).orElseGet(() -> {
			newFilm.setFilmID(filmID);
			return filmRepo.save(newFilm);
		 });
		return film;
	}

	@GetMapping("/all_actors/{actorID}")
	public @ResponseBody
	Actor all_actors(@PathVariable("actorID") int actorID){
		Actor actor = actorRepo.findById(actorID).
				orElseThrow(() -> new ResourceAccessException("Actor ID doesn't exist: " + actorID));
		return actor;
	}

	@PostMapping("/add_film")
	Film newFilm(@RequestBody Film newFilm){
		return filmRepo.save(newFilm);
	}

	@DeleteMapping("/delete_actor/{filmID}")
	void deleteFilm(@PathVariable("filmID") int filmID){
		filmRepo.deleteById(filmID);
	}

	@GetMapping("/all_films")
	public @ResponseBody
	ArrayList<FilmCatIntf> all_films(){
		ArrayList<FilmCatIntf> allFilms = filmRepo.findAllFilms();


		return allFilms;
	}

	@GetMapping("/all_films/{category}")
	public @ResponseBody
	ArrayList<FilmCatIntf> search_category(@PathVariable("category") String category){
		return filmRepo.searchCategory(category);
	}

	// view film details
	@GetMapping("/view_film/{filmID}")
	public @ResponseBody
	ArrayList<FilmDetails> view_film(@PathVariable("filmID") int filmID){
		return filmRepo.view_film(filmID);
	}

	// all actors in a given film
	@GetMapping("/view_film/{filmID}/all_actors")
	public @ResponseBody
	ArrayList<FilmActorIntf> film_all_actors(@PathVariable("filmID") int filmID){
		return filmRepo.film_all_actors(filmID);
	}

	@GetMapping("/view_film/{filmID}/similar_films")
	public @ResponseBody
	ArrayList<FilmDetails> similar_films(@PathVariable("filmID") int filmID){
		return filmRepo.similar_films(filmID);
	}

	//@GetMapping


}
