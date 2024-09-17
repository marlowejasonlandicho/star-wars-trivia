package exam.trivia.starwars.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import exam.trivia.starwars.entity.Film;
import exam.trivia.starwars.entity.People;

public interface FilmRepository extends CrudRepository<Film, Long> {
	Film findByTitle(String title);

	Set<Film> findByPeople(People people);
}
