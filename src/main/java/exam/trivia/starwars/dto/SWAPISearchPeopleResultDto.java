package exam.trivia.starwars.dto;

import java.util.Set;

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
public class SWAPISearchPeopleResultDto {
	
	private Set<SWAPISearchPeopleDto> results;
	
}
