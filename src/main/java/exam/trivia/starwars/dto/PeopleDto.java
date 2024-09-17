package exam.trivia.starwars.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeopleDto {

	@JsonIgnore
	private Long peopleId;

	private String name;

	private String height;

	private String mass;

	private String gender;

	private Set<FilmDto> films;

	private Set<VehicleDto> vehicles;

}
