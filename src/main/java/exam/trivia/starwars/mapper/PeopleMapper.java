package exam.trivia.starwars.mapper;

import org.mapstruct.Mapper;

import exam.trivia.starwars.dto.PeopleDto;
import exam.trivia.starwars.entity.People;

@Mapper(componentModel = "spring")
public interface PeopleMapper {

	PeopleDto peopleToPeopleDto(People people);

	People peopleDtoToPeople(PeopleDto peopleDto);

}
