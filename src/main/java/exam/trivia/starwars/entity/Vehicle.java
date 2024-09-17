package exam.trivia.starwars.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "vehicle")
@Data
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vehicle_id")
	private Long vehicleId;

	@Column(unique = true)
	private String model;

	private String name;

	private String manufacturer;

	@Column(name = "cost_in_credits")

	private String costInCredits;

	private String length;

	@Column(name = "max_atmosphering_speed")
	private String maxAtmospheringSpeed;

	private String crew;

	private String passengers;

	@Column(name = "cargo_capacity")
	private String cargoCapacity;

	private String consumables;

	@Column(name = "vehicle_class")
	private String vehicleClass;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "vehicles")
	private Set<People> people;

}
