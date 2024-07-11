package com.OOP.ObjectOrientedCarDealership.repositories;

import com.OOP.ObjectOrientedCarDealership.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

@Repository
@Component
public class VehicleRepository {

    private static final Logger logger = Logger.getLogger(VehicleRepository.class.getName());

    @Autowired
    public DataSource dataSource;

    public List<Vehicle> getAllVehicles() {
        String query = """
        SELECT
                v.vehicle_id,
                v."VIN",
                v.sold,
                v.color,
                v.electric,
                v.miles,
                v.doors,
                v.convertible,
                v.make,
                v.model,
                v.year,
                v.price,
                vt.type AS vehicle_type
        FROM
        vehicles v
        JOIN
        vehicle_types vt ON v.vehicle_type = vt.type_id;""";


        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                vehicles.add(mapToVehicle(rs));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
        }

        return vehicles;
    }

    public List<Vehicle> getVehiclesByPrice(float min, float max) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = """
        SELECT
                v.vehicle_id,
                v."VIN",
                v.sold,
                v.color,
                v.electric,
                v.miles,
                v.doors,
                v.convertible,
                v.make,
                v.model,
                v.year,
                v.price,
                vt.type AS vehicle_type
        FROM
        vehicles v
        JOIN
        vehicle_types vt ON v.vehicle_type = vt.type_id
        WHERE v.price > ? AND v.price < ?;""";

        try(Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ps.setFloat(1, min);
            ps.setFloat(2, max);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vehicles.add(mapToVehicle(rs));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
        }
        return vehicles;
    }

    public List<Vehicle> getVehicleByMakeModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = """
        SELECT
                v.vehicle_id,
                v."VIN",
                v.sold,
                v.color,
                v.electric,
                v.miles,
                v.doors,
                v.convertible,
                v.make,
                v.model,
                v.year,
                v.price,
                vt.type AS vehicle_type
        FROM
        vehicles v
        JOIN
        vehicle_types vt ON v.vehicle_type = vt.type_id
        WHERE UPPER(v.make) = UPPER(?) AND UPPER(v.model) = UPPER(?);""";

        try(Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ps.setString(1, make);
            ps.setString(2, model);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vehicles.add(mapToVehicle(rs));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByYearRange(int lowestYear, int highestYear) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = """
        SELECT
                v.vehicle_id,
                v."VIN",
                v.sold,
                v.color,
                v.electric,
                v.miles,
                v.doors,
                v.convertible,
                v.make,
                v.model,
                v.year,
                v.price,
                vt.type AS vehicle_type
        FROM
        vehicles v
        JOIN
        vehicle_types vt ON v.vehicle_type = vt.type_id
        WHERE v.year >= ? AND v.year <= ?;""";

        try(Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ps.setInt(1, lowestYear);
            ps.setInt(2, highestYear);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vehicles.add(mapToVehicle(rs));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = """
        SELECT
                v.vehicle_id,
                v."VIN",
                v.sold,
                v.color,
                v.electric,
                v.miles,
                v.doors,
                v.convertible,
                v.make,
                v.model,
                v.year,
                v.price,
                vt.type AS vehicle_type
        FROM
        vehicles v
        JOIN
        vehicle_types vt ON v.vehicle_type = vt.type_id
        WHERE UPPER(v.color) = UPPER(?);""";

        try(Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ps.setString(1, color);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vehicles.add(mapToVehicle(rs));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByMileRange(int lowestYear, int highestYear) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = """
        SELECT
                v.vehicle_id,
                v."VIN",
                v.sold,
                v.color,
                v.electric,
                v.miles,
                v.doors,
                v.convertible,
                v.make,
                v.model,
                v.year,
                v.price,
                vt.type AS vehicle_type
        FROM
        vehicles v
        JOIN
        vehicle_types vt ON v.vehicle_type = vt.type_id
        WHERE v.miles >= ? AND v.miles <= ?;""";

        try(Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ps.setInt(1, lowestYear);
            ps.setInt(2, highestYear);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vehicles.add(mapToVehicle(rs));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
        }
        return vehicles;
    }

    public Vehicle removeVehicleById(int id) {
        Vehicle vehicle = new Vehicle();
        String query = """
        DELETE
        FROM
        vehicles
        WHERE vehicle_id = ?;""";

        try(Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vehicle = mapToVehicle(rs);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
        }
        return vehicle;
    }

    Vehicle mapToVehicle(ResultSet rs) {
        Vehicle newVehicle = new Vehicle();
        try {
            int vehicleId = rs.getInt("vehicle_id");
            String VIN = rs.getString("VIN");
            boolean sold = rs.getBoolean("sold");
            String color = rs.getString("color");
            boolean electric = rs.getBoolean("electric");
            int miles = rs.getInt("miles");
            int doors = rs.getInt("doors");
            boolean convertible = rs.getBoolean("convertible");
            String make = rs.getString("make");
            String model = rs.getString("model");
            int year = rs.getInt("year");
            float price = rs.getFloat("price");
            String vehicleType = rs.getString("vehicle_type");
            newVehicle =  new Vehicle(vehicleId, VIN, sold, color, electric, miles,
                    doors, convertible, make, model, year, price, vehicleType);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return newVehicle;
    }
}
