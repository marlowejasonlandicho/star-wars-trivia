package exam.trivia.starwars.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import exam.trivia.starwars.dto.FilmDto;
import exam.trivia.starwars.dto.PeopleDto;
import exam.trivia.starwars.dto.SearchResultDto;
import exam.trivia.starwars.dto.VehicleDto;
import exam.trivia.starwars.entity.Film;
import exam.trivia.starwars.entity.People;
import exam.trivia.starwars.entity.Vehicle;
import exam.trivia.starwars.mapper.FilmMapper;
import exam.trivia.starwars.mapper.PeopleMapper;
import exam.trivia.starwars.mapper.VehicleMapper;
import exam.trivia.starwars.repository.FilmRepository;
import exam.trivia.starwars.repository.PeopleRepository;
import exam.trivia.starwars.repository.VehicleRepository;

/**
 * 
 * Search People from Database Service
 * 
 */
@Service
public class SearchPeopleFromDBService {

	private final Log LOG = LogFactory.getLog(getClass());

	private final PeopleRepository peopleRepository;

	private final FilmRepository filmRepository;

	private final VehicleRepository vehicleRepository;

	private final PeopleMapper peopleMapper;

	private final FilmMapper filmMapper;

	private final VehicleMapper vehicleMapper;

	public SearchPeopleFromDBService(PeopleRepository peopleRepository, FilmRepository filmRepository,
			VehicleRepository vehicleRepository, PeopleMapper peopleMapper, FilmMapper filmMapper,
			VehicleMapper vehicleMapper) {
		this.peopleRepository = peopleRepository;
		this.filmRepository = filmRepository;
		this.vehicleRepository = vehicleRepository;
		this.peopleMapper = peopleMapper;
		this.filmMapper = filmMapper;
		this.vehicleMapper = vehicleMapper;
	}

	public SearchResultDto searchPeople(String searchText) {
		SearchResultDto searchResult = new SearchResultDto();
		Set<PeopleDto> people = new HashSet<>();

		Set<People> peopleFromDBSet = this.peopleRepository.findByNameContaining(searchText);


		peopleFromDBSet.stream().forEach(peopleFromDB -> {
			Set<FilmDto> peopleFilmsDto = new HashSet<>();
			Set<VehicleDto> peopleVehiclesDto = new HashSet<>();
			
			Set<Film> peopleFilms = this.filmRepository.findByPeople(peopleFromDB);
			Set<Vehicle> peopleVehicles = this.vehicleRepository.findByPeople(peopleFromDB);
			
			peopleFilms.stream().forEach(peopleFilm -> {
				FilmDto filmDto = this.filmMapper.filmToFilmDto(peopleFilm);
				peopleFilmsDto.add(filmDto);
			});
			peopleVehicles.stream().forEach(peopleVehicle -> {
				VehicleDto vehicleDto = this.vehicleMapper.vehicleToVehicleDto(peopleVehicle);
				peopleVehiclesDto.add(vehicleDto);
			});
			
			PeopleDto peopleDto = new PeopleDto();
			peopleDto.setName(peopleFromDB.getName());
			peopleDto.setFilms(peopleFilmsDto);
			peopleDto.setVehicles(peopleVehiclesDto);
			people.add(peopleDto);
		});

		searchResult.setPeople(people);

		return searchResult;
	}

}
