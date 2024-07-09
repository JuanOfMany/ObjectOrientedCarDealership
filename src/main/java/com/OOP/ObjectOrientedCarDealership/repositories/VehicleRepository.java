package com.OOP.ObjectOrientedCarDealership.repositories;

import com.OOP.ObjectOrientedCarDealership.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
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
public class VehicleRepository {

    private static final Logger logger = Logger.getLogger(VehicleRepository.class.getName());

    @Autowired
    private DataSource dataSource;

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
                vehicles.add(new Vehicle(vehicleId, VIN, sold, color, electric, miles,
                        doors, convertible, make, model, year, price, vehicleType));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
        }

        return vehicles;
    }
}
