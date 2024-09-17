package exam.trivia.starwars.service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import exam.trivia.starwars.dto.FilmDto;
import exam.trivia.starwars.dto.PeopleDto;
import exam.trivia.starwars.dto.SWAPISearchPeopleResultDto;
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
 * Star Wars API Search People Service
 * 
 */
@Service
public class StarwarsAPIService {

	private final Log LOG = LogFactory.getLog(getClass());

	/**
	 * 
	 * Star Wars Search API URL path from configuration file
	 * 
	 */
	@Value("${swapi.search-people-path}")
	private String searchPeoplePath;

	private final RestClient restClient;

	private final PeopleRepository peopleRepository;

	private final FilmRepository filmRepository;

	private final VehicleRepository vehicleRepository;

	private final PeopleMapper peopleMapper;

	private final FilmMapper filmMapper;

	private final VehicleMapper vehicleMapper;

	public StarwarsAPIService(RestClient restClient, PeopleRepository peopleRepository, FilmRepository filmRepository,
			VehicleRepository vehicleRepository, PeopleMapper peopleMapper, FilmMapper filmMapper,
			VehicleMapper vehicleMapper) {
		this.restClient = restClient;
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

		SWAPISearchPeopleResultDto swapiSearchPeopleResultDto = this.restClient.get()
				.uri(this.searchPeoplePath, searchText).accept(MediaType.APPLICATION_JSON).retrieve()
				.body(SWAPISearchPeopleResultDto.class);

		swapiSearchPeopleResultDto.getResults().stream().forEach(swapiSearchPeopleResult -> {
			PeopleDto peopleDto = new PeopleDto();
			Set<FilmDto> films = new HashSet<>();
			Set<VehicleDto> vehicles = new HashSet<>();

			peopleDto.setName(swapiSearchPeopleResult.getName());

			swapiSearchPeopleResult.getFilms().stream().forEach(filmUrl -> {
				FilmDto filmDto = this.restClient.get().uri(filmUrl).accept(MediaType.APPLICATION_JSON).retrieve()
						.body(FilmDto.class);
				films.add(filmDto);
			});

			swapiSearchPeopleResult.getVehicles().stream().forEach(vehicleUrl -> {
				VehicleDto vehicleDto = this.restClient.get().uri(vehicleUrl).accept(MediaType.APPLICATION_JSON)
						.retrieve().body(VehicleDto.class);
				vehicles.add(vehicleDto);
			});

			peopleDto.setFilms(films);
			peopleDto.setVehicles(vehicles);

			people.add(peopleDto);
		});

		searchResult.setPeople(people);

		return searchResult;
	}

	@Transactional
	public SearchResultDto saveSearchResult(SearchResultDto searchResultDto) {
		searchResultDto.getPeople().stream().forEach(peopleDto -> {
			People peopleFromDB = this.peopleRepository.findByName(peopleDto.getName());
			if (Objects.isNull(peopleFromDB)) {
				People people = new People();
				people.setName(peopleDto.getName());

				peopleDto.getFilms().stream().forEach(filmDto -> {
					Film filmFromDB = this.filmRepository.findByTitle(filmDto.getTitle());
					if (Objects.nonNull(filmFromDB)) {
						people.getFilms().add(filmFromDB);
					} else {
						people.getFilms().add(this.filmMapper.filmDtoToFilm(filmDto));
					}

				});

				peopleDto.getVehicles().stream().forEach(vehicleDto -> {
					Vehicle vehicleFromDB = this.vehicleRepository.findByModel(vehicleDto.getModel());
					if (Objects.nonNull(vehicleFromDB)) {
						people.getVehicles().add(vehicleFromDB);
					} else {
						people.getVehicles().add(this.vehicleMapper.vehicleDtoToVehicle(vehicleDto));
					}
				});
				this.peopleRepository.save(people);
			}
		});

		return searchResultDto;
	}
}
