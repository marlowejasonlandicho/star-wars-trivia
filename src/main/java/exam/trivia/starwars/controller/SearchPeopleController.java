package exam.trivia.starwars.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import exam.trivia.starwars.dto.SearchResultDto;
import exam.trivia.starwars.service.SearchPeopleFromDBService;
import exam.trivia.starwars.service.StarwarsAPIService;

@Controller
public class SearchPeopleController {

	private final Log LOG = LogFactory.getLog(getClass());

	private final StarwarsAPIService starwarsAPIService;

	private final SearchPeopleFromDBService searchPeopleFromDBService;
	
	public SearchPeopleController(StarwarsAPIService searchPeopleService,SearchPeopleFromDBService searchPeopleFromDBService) {
		super();
		this.starwarsAPIService = searchPeopleService;
		this.searchPeopleFromDBService = searchPeopleFromDBService;
	}

	@QueryMapping
	public SearchResultDto searchPeople(@Argument String searchText) {
		LOG.info(searchText);
		return this.starwarsAPIService.searchPeople(searchText);
	}

	@MutationMapping
	public SearchResultDto saveSearchResult(@Argument(value = "searchResultInput") SearchResultDto searchResultInput) {
		return this.starwarsAPIService.saveSearchResult(searchResultInput);
	}

	
	@QueryMapping
	public SearchResultDto searchPeopleFromDB(@Argument String searchText) {
		LOG.info(searchText);
		return this.searchPeopleFromDBService.searchPeople(searchText);
	}
	
}
