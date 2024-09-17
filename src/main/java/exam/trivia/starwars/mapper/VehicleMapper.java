package exam.trivia.starwars.mapper;

import org.mapstruct.Mapper;

import exam.trivia.starwars.dto.VehicleDto;
import exam.trivia.starwars.entity.Vehicle;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

	VehicleDto vehicleToVehicleDto(Vehicle vehicle);

	Vehicle vehicleDtoToVehicle(VehicleDto vehicleDto);

}
