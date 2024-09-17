package exam.trivia.starwars.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import exam.trivia.starwars.entity.People;

public interface PeopleRepository extends CrudRepository<People, Long> {
	People findByName(String name);

	Set<People> findByNameContaining(String name);
}
