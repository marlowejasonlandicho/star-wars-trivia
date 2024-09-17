package exam.trivia.starwars.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "people")
@Data
public class People {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "people_id")
	private Long peopleId;

	@Column(unique = true)
	private String name;

	private String height;

	private String mass;

	private String gender;

//	@OneToMany(mappedBy = "people", cascade = CascadeType.ALL)

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "people_film", joinColumns = { @JoinColumn(name = "people_id") }, inverseJoinColumns = {
			@JoinColumn(name = "film_id") })
	private Set<Film> films = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "people_vehicle", joinColumns = { @JoinColumn(name = "people_id") }, inverseJoinColumns = {
			@JoinColumn(name = "vehicle_id") })
	private Set<Vehicle> vehicles = new HashSet<>();

}
