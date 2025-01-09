package org.example.dto.Route;

import jakarta.validation.constraints.*;
import org.example.entity.Client;
import org.example.entity.Company;
import org.example.entity.Employee;
import org.example.entity.Vehicle;
import org.example.enums.CargoType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateRouteDto {

    @NotBlank(message = "Route must have a starting point.")
    private String startingPoint;

    @NotBlank(message = "Route must have a destination.")
    private String destination;

    @FutureOrPresent(message = "Starting date must be in the present or in the future.")
    private LocalDate startDate;

    @Future(message = "Delivery date must be in the future.")
    private LocalDate deliveryDate;

    @NotNull(message = "Cargo type cannot be empty.")
    private CargoType cargoType;

    @NotNull(message = "Paid cannot be empty.")
    private boolean isPaid;

    @PositiveOrZero(message = "Cost must not be negative number")
    private BigDecimal cost;

    @PositiveOrZero(message = "Weight must not be negative number")
    private BigDecimal weight;

    @NotNull(message = "Route must be have at least one vehicle")
    private Vehicle vehicle;

    @NotNull(message = "Route must be have company")
    private Company company;

    @NotNull(message = "Route must be have at least one employee")
    private Employee employee;

    @NotNull(message = "Route must be have at least one client")
    private Client client;

    public CreateRouteDto() {
    }

    public CreateRouteDto(
            String startingPoint,
            String destination,
            LocalDate startDate,
            LocalDate deliveryDate,
            CargoType cargoType,
            boolean isPaid,
            BigDecimal cost,
            Vehicle vehicle,
            Company company,
            Employee employee,
            Client client) {
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.startDate = startDate;
        this.deliveryDate = deliveryDate;
        this.cargoType = cargoType;
        this.isPaid = isPaid;
        this.cost = cost;
        this.vehicle = vehicle;
        this.company = company;
        this.employee = employee;
        this.client = client;
    }

    public CreateRouteDto(
            String startingPoint,
            String destination,
            LocalDate startDate,
            LocalDate deliveryDate,
            CargoType cargoType,
            boolean isPaid,
            BigDecimal cost,
            BigDecimal weight,
            Vehicle vehicle,
            Company company,
            Employee employee,
            Client client) {
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.startDate = startDate;
        this.deliveryDate = deliveryDate;
        this.cargoType = cargoType;
        this.isPaid = isPaid;
        this.cost = cost;
        this.weight = weight;
        this.vehicle = vehicle;
        this.company = company;
        this.employee = employee;
        this.client = client;
    }

    public String getStartingPoint() { return startingPoint; }
    public String getDestination() { return destination; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getDeliveryDate() { return deliveryDate; }
    public CargoType getCargoType() { return cargoType; }
    public boolean isPaid() { return isPaid; }
    public BigDecimal getCost() { return cost; }
    public BigDecimal getWeight() { return weight; }
    public Vehicle getVehicle() { return vehicle; }
    public Company getCompany() { return company; }
    public Employee getEmployee() { return employee; }
    public Client getClient() { return client; }

    public void setStartingPoint(String startingPoint) { this.startingPoint = startingPoint; }
    public void setDestination(String destination) { this.destination = destination; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }
    public void setCargoType(CargoType cargoType) { this.cargoType = cargoType; }
    public void setPaid(boolean paid) { this.isPaid = paid; }
    public void setCost(BigDecimal cost) { this.cost = cost; }
    public void setWeight(BigDecimal weight) { this.weight = weight; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }
    public void setCompany(Company company) { this.company = company; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public void setClient(Client client) { this.client = client; }

    @Override
    public String toString() {
        return "CreateRouteDto{" +
                "startingPoint='" + startingPoint + '\'' +
                ", destination='" + destination + '\'' +
                ", startDate=" + startDate +
                ", deliveryDate=" + deliveryDate +
                ", cargoType=" + cargoType +
                ", isPaid=" + isPaid +
                ", cost=" + cost +
                ", weight=" + weight +
                ", vehicle=" + vehicle +
                ", company=" + company +
                ", employee=" + employee +
                ", client=" + client +
                '}';
    }
}
