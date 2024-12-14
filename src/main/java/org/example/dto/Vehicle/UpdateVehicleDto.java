package org.example.dto.Vehicle;

public class UpdateVehicleDto {

    private long id;
    private String plate;

    public UpdateVehicleDto(long id, String plate) {
        this.id = id;
        this.plate = plate;
    }
    public long getId() { return id; }
    public String getPlate() { return plate; }

    public void setId(long id) { this.id = id; }
    public void setPlate(String plate) { this.plate = plate; }

    @Override
    public String toString() {
        return "UpdateVehicleDto{" +
                "id=" + id +
                ", plate='" + plate + '\'' +
                '}';
    }
}
