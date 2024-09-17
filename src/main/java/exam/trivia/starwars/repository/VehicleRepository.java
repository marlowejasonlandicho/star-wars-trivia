package exam.trivia.starwars.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import exam.trivia.starwars.entity.People;
import exam.trivia.starwars.entity.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
	Vehicle findByModel(String model);

	Set<Vehicle> findByPeople(People people);
}
