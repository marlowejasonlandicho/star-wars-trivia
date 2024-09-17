package exam.trivia.starwars.mapper;

import org.mapstruct.Mapper;

import exam.trivia.starwars.dto.FilmDto;
import exam.trivia.starwars.entity.Film;

@Mapper(componentModel = "spring")
public interface FilmMapper {

	FilmDto filmToFilmDto(Film film);

	Film filmDtoToFilm(FilmDto filmDto);

}
