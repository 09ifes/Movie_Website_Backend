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
@RequestMapping("/")
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
	@GetMapping("/all_films")
	public @ResponseBody
	ArrayList<FilmDetails> all_films(){
		ArrayList<FilmDetails> allFilms = filmRepo.findAllFilms();
		return allFilms;
	}
	@GetMapping("/most_popular")
	public @ResponseBody
	ArrayList<FilmDetails> most_popular(){
		return filmRepo.mostPopular();
	}

	@GetMapping("/most_recent")
	public @ResponseBody
	ArrayList<FilmDetails> most_recent(){
		return filmRepo.mostRecent();
	}

	@GetMapping("search_films")
	public @ResponseBody
	ArrayList<FilmDetails> search_films(@RequestBody String film_name){
		return filmRepo.searchFilm();
	}

	@GetMapping("/get_film/{filmID}")
	public @ResponseBody
	Film getFilmByID(@PathVariable("filmID") int filmID){
		Film film = filmRepo.findById(filmID).
				orElseThrow(() -> new ResourceAccessException("Film ID doesn't exist: " + filmID));
		return film;
	}

	@PostMapping("/add_film")
	Film newFilm(@RequestBody Film newFilm){
		return filmRepo.save(newFilm);
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

	@DeleteMapping("/delete_film/{filmID}")
	void deleteFilm(@PathVariable("filmID") int filmID){
		filmRepo.deleteById(filmID);
	}

	@GetMapping("/all_films/{category}")
	public @ResponseBody
	ArrayList<FilmDetails> search_category(@PathVariable("category") String category){
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
		ArrayList<FilmDetails> film = filmRepo.view_film(filmID);
		String catName = film.get(0).getname();
		return filmRepo.similar_films(filmID, catName);
	}


	@GetMapping("/actor/{actorID}")
	public @ResponseBody
	Actor find_actor(@PathVariable("actorID") int actorID){
		Actor actor = actorRepo.findById(actorID).
				orElseThrow(() -> new ResourceAccessException("Actor ID doesn't exist: " + actorID));
		return actor;
	}

	@PostMapping("/add_actor")
	Actor newActor(@RequestBody Actor newActor) {
		return actorRepo.save(newActor);
	}

	@PutMapping("/edit_actor/{actorID}")
	Actor editActor(@RequestBody Actor newActor, @PathVariable("actorID") int actorID){
		Actor actor = actorRepo.findById(actorID).map(actor1 -> {
			actor1.setFirstName(newActor.getFirstName());
			actor1.setLastName(newActor.getLastName());
			return actorRepo.save(actor1);

		}).orElseGet(() -> {
			newActor.setActorID(actorID);
			return actorRepo.save(newActor);
		});
		return actor;
	}







}
