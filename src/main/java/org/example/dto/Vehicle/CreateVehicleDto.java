package org.example.dto.Vehicle;

import jakarta.validation.constraints.NotBlank;
import org.example.enums.VehicleType;

public class CreateVehicleDto {

    @NotBlank(message = "Vehicle must have a plate number")
    private String plate;

    @NotBlank(message = "Vehicle must have a type")
    private VehicleType type;

    public CreateVehicleDto(String plate, VehicleType type) {
        this.plate = plate;
        this.type = type;
    }

    public String getPlate() { return plate; }
    public VehicleType getType() { return type; }

    public void setPlate(String plate) {
        this.plate = plate;
    }
    public void setType(VehicleType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CreateVehicleDto{" +
                "plate='" + plate + '\'' +
                ", type=" + type +
                '}';
    }
}
