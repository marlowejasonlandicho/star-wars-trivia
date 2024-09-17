package exam.trivia.starwars.dto;

import java.util.List;

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
public class SWAPISearchPeopleDto {
	
	private String name;
	
	private String height;
	
	private String mass;
	
	private String gender;
	
	private List<String> films;
	
	private List<String> vehicles;
	
}
