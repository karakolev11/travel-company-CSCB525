package org.example;

import org.example.configuration.FileUtils;
import org.example.configuration.SessionFactoryUtil;
import org.example.dao.*;
import org.example.dto.Client.CreateClientDto;
import org.example.dto.Company.CreateCompanyDto;
import org.example.dto.Employee.CreateEmployeeDto;
import org.example.dto.Route.CreateRouteDto;
import org.example.dto.Vehicle.CreateVehicleDto;
import org.example.entity.*;
import org.example.enums.CargoType;
import org.example.enums.Category;
import org.example.enums.SortType;
import org.example.enums.VehicleType;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        try {
        SessionFactoryUtil.getSessionFactory().openSession();
        } catch(HibernateException e) {
            System.out.printf(e.getMessage());
        }

        // Create new company
        CreateCompanyDto createCompanyDto = new CreateCompanyDto("DHL", "Courier company");
        CompanyDao.createCompany(createCompanyDto);

        //Print company
        Company dhl = CompanyDao.getCompanyById(1);
        System.out.println(dhl);

        //Assign employee to this company
        CreateEmployeeDto createEmployee1 = new CreateEmployeeDto("Ivan Karakolev", Category.BUS_DRIVER, BigDecimal.valueOf(5000), dhl);
        CreateEmployeeDto createEmployee2 = new CreateEmployeeDto("Simona Dimitrova", Category.SPECIAL_DELIVERY_DRIVER, BigDecimal.valueOf(3000), dhl);
        EmployeeDao.createEmployee(createEmployee1);
        EmployeeDao.createEmployee(createEmployee2);

        //Print employees
        List<Employee> employees = EmployeeDao.getAllEmployees();
        System.out.println(employees);

        //Assign vehicles to this company
        CreateVehicleDto createVehicleDto1 = new CreateVehicleDto("BT2649MA", VehicleType.BUS, dhl);
        CreateVehicleDto createVehicleDto2 = new CreateVehicleDto("BT2663KM", VehicleType.SPECIAL_TRUCK, dhl);
        VehicleDao.createVehicle(createVehicleDto1);
        VehicleDao.createVehicle(createVehicleDto2);

        //Print vehicles
        List<Vehicle> vehicles = VehicleDao.getCompanyVehicles(dhl.getId());
        System.out.println(vehicles);

        //Create a client
        CreateClientDto createClient1 = new CreateClientDto("Apple");
        ClientDao.createClient(createClient1);

        //Print client
        Client client1 = ClientDao.getClientById(1);
        System.out.println(client1);

        //Get vehicle and employee for route
        Vehicle bus = VehicleDao.getVehicleById(1);
        Vehicle specialVehicle = VehicleDao.getVehicleById(2);

        Employee employee1 = EmployeeDao.getEmployeeById(1);
        Employee employee2 = EmployeeDao.getEmployeeById(2);

        //Create a route
        CreateRouteDto createRoute1 = new CreateRouteDto(
                "Sofia",
                "Veliko Tarnovo",
                LocalDate.now(),
                LocalDate.of(2025,1,20),
                CargoType.PEOPLE,
                false,
                BigDecimal.valueOf(500),
                bus,
                dhl,
                employee1,
                client1
        );

        CreateRouteDto createRoute2 = new CreateRouteDto(
                "Pleven",
                "Plovdiv",
                LocalDate.now(),
                LocalDate.of(2025,2,2),
                CargoType.FUEL,
                true,
                BigDecimal.valueOf(150),
                BigDecimal.valueOf(1000),
                specialVehicle,
                dhl,
                employee2,
                client1
        );

        RouteDao.createRoute(createRoute1);
        RouteDao.createRoute(createRoute2);

        //Get routes
        List<Route> routes = RouteDao.getAllRoutes();
        System.out.println(routes);

        //Save to file
        FileUtils.saveToFile("list-of-routes-01");

        //Load from file
        FileUtils.loadFromFile("predefinedRoutes.txt");

        //Reports
        ReportsDao.showRoutesCostPerCompany();
        ReportsDao.showRoutesPerCompany();
        ReportsDao.showRevenueForPeriod(1, LocalDate.now(), LocalDate.of(2025,1,31));

        //Sort and filter
        List<Employee> sortEmployees = EmployeeSortAndFilterDao.sortEmployeeByCategoryAlphabetically();
        System.out.println(sortEmployees);

        List<Employee> sortEmployeesBySalary = EmployeeSortAndFilterDao.sortEmployeeBySalary(SortType.ASC);
        System.out.println(sortEmployeesBySalary);

    }
}