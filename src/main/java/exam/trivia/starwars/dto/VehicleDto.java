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
public class VehicleDto {

	@JsonIgnore
	private Long vehicleId;

	private String name;

	private String model;

	private String manufacturer;

	@JsonProperty("cost_in_credits")
	private String costInCredits;

	private String length;

	@JsonProperty("max_atmosphering_speed")
	private String maxAtmospheringSpeed;

	private String crew;

	private String passengers;

	@JsonProperty("cargo_capacity")
	private String cargoCapacity;

	private String consumables;

	@JsonProperty("vehicle_class")
	private String vehicleClass;

}
