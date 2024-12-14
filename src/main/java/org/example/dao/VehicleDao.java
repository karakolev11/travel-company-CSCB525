package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.dto.Vehicle.CreateVehicleDto;
import org.example.dto.Vehicle.UpdateVehicleDto;
import org.example.entity.Route;
import org.example.entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class VehicleDao {
    public static void createVehicle(CreateVehicleDto vehicleDto) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(vehicleDto);
            transaction.commit();
        }
    }

    public static Vehicle getVehicleById(long id) {
        Vehicle vehicle;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicle = session.get(Vehicle.class, id);
            transaction.commit();
        }

        return vehicle;
    }

    public static List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicles = session
                    .createQuery("Select v from Vehicle v", Vehicle.class)
                    .getResultList();
            transaction.commit();
        }

        return vehicles;
    }

    public static List<Vehicle> getCompanyVehicles(long companyId) {
        List<Vehicle> vehicles;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicles = session
                    .createQuery(
                            "Select v from Vehicle v" +
                                    " where v.company.id = :companyId",
                            Vehicle.class)
                    .setParameter("companyId", companyId)
                    .getResultList();
            transaction.commit();
        }

        return vehicles;
    }

    public static List<Route> getAllRoutesDoneByVehicle(long vehicleId) {
        List<Route> routes;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            routes = session
                    .createQuery(
                            "Select r from Route r" +
                                    " where r.vehicle.id = :vehicleId",
                            Route.class)
                    .setParameter("vehicleId", vehicleId)
                    .getResultList();
            transaction.commit();
        }

        return routes;
    }

    public static void updateVehiclePlate(UpdateVehicleDto updateVehicleDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE Vehicle v SET v.plate = :plate WHERE v.id = :id")
                    .setParameter("plate", updateVehicleDto.getPlate())
                    .setParameter("id", updateVehicleDto.getId())
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void softDeleteVehicle(long vehicleId) {
        LocalDate deleteDate = LocalDate.now();
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE Vehicle v SET v.deleted_at = :deleteDate WHERE v.id = :id")
                    .setParameter("deleteDate", deleteDate)
                    .setParameter("id", vehicleId)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void hardDeleteCompany(Vehicle vehicle) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(vehicle);
            transaction.commit();
        }
    }
}
