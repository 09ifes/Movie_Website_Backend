package UIElements.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import org.json.JSONObject;
import java.util.ArrayList;

@SpringBootApplication
@RestController
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

	@PostMapping("/search_films")
	public @ResponseBody
	ArrayList<FilmDetails> search_films(@RequestBody Object data) throws JsonProcessingException {
		String json = (String) data;
		JSONObject jsonObject1 = new JSONObject(json);
		String title = (String) jsonObject1.get("title");
		String sqlVariable = "%" + title + "%";  // converts to correct sql syntax
		return filmRepo.searchFilm(sqlVariable);
	}

	@GetMapping("/get_film/{filmID}")
	public @ResponseBody
	Film getFilmByID(@PathVariable("filmID") int filmID){
		Film film = filmRepo.findById(filmID).
				orElseThrow(() -> new ResourceAccessException("Film ID doesn't exist: " + filmID));
		return film;
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
		ArrayList<FilmDetails> film = filmRepo.view_film(filmID);
		return film;
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


	@PostMapping("/add_film")
	void newFilm(@RequestBody Object data){
		System.out.println(data);
		String json = (String) data;
		JSONObject jsonObject1 = new JSONObject(json);

		String title = (String) jsonObject1.get("title");
		String description = (String) jsonObject1.get("description");
		String img_url = (String) jsonObject1.get("img_url");
		String vid_url = (String) jsonObject1.get("vid_url");
		filmRepo.add_film(title, description, img_url, vid_url);
	}


	@PutMapping("/edit_film/{filmID}")
	void editFilmByID(@RequestBody Object data, @PathVariable("filmID") int filmID){
		System.out.println(data);
		String json = (String) data;
		JSONObject jsonObject1 = new JSONObject(json);

		String title = (String) jsonObject1.get("title");
		String description = (String) jsonObject1.get("description");
		String img_url = (String) jsonObject1.get("img_url");
		String vid_url = (String) jsonObject1.get("vid_url");
		filmRepo.edit_film(title, description, img_url, vid_url, filmID);
	}


	@DeleteMapping("/delete_film/{filmID}")
	void deleteFilm(@PathVariable("filmID") int filmID){
		filmRepo.deleteById(filmID);
	}


	@GetMapping("/actor/{actorID}")
	public @ResponseBody
	Actor find_actor(@PathVariable("actorID") int actorID){
		Actor actor = actorRepo.findById(actorID).
				orElseThrow(() -> new ResourceAccessException("Actor ID doesn't exist: " + actorID));
		return actor;
	}


	@PostMapping("/add_actor")
	public @ResponseBody Actor addActor(@RequestBody Actor newActor) {
		Actor actor1 = actorRepo.save(newActor);
		return actor1;
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


	@DeleteMapping("/delete_actor/{actorID}")
	void deleteActor(@PathVariable("actorID") int actorID){
		actorRepo.deleteById(actorID);
	}






}
