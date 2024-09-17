package exam.trivia.starwars.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class FilmDto {

	@JsonIgnore
	private Long filmId;

	private String title;

	@JsonProperty("episode_id")
	private Integer episodeId;

	private String director;

	private String producer;

	@JsonProperty("release_date")
	private String releaseDate;

}
