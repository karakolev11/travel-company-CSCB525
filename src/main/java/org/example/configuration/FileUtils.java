package org.example.configuration;

import org.example.dao.*;
import org.example.dto.Route.CreateRouteDto;
import org.example.entity.*;
import org.example.enums.CargoType;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    public static void saveToFile(String filename) {
        List<Route> allRoutes = RouteDao.getAllRoutes();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Route route : allRoutes) {
                writer.write("Route " + (route.getId() + 1) + ":");
                writer.newLine();
                writer.write(route.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFromFile(String file) {
        List<CreateRouteDto> routes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                CreateRouteDto route = new CreateRouteDto();
                route.setCargoType(CargoType.valueOf(data[0].trim().toUpperCase()));
                route.setCost(new BigDecimal(data[1].trim()));
                route.setDeliveryDate(LocalDate.parse(data[2].trim()));
                route.setDestination(data[3]);
                route.setPaid(Boolean.parseBoolean(data[4].trim()));
                route.setStartDate(LocalDate.parse(data[5].trim()));
                route.setStartingPoint(data[6]);
                route.setWeight(new BigDecimal(data[7].trim()));
                Client client = ClientDao.getClientById(Long.parseLong(data[8].trim()));
                Company company = CompanyDao.getCompanyById(Long.parseLong(data[9].trim()));
                Employee employee = EmployeeDao.getEmployeeById(Long.parseLong(data[10].trim()));
                Vehicle vehicle = VehicleDao.getVehicleById(Long.parseLong(data[11].trim()));
                route.setVehicle(vehicle);
                route.setCompany(company);
                route.setEmployee(employee);
                route.setClient(client);
                routes.add(route);
                System.out.println(Arrays.toString(data));
            }
            for (CreateRouteDto route : routes) {
                RouteDao.createRoute(route);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
