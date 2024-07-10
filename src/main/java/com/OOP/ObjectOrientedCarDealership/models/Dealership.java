package com.OOP.ObjectOrientedCarDealership.models;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;

    List<Vehicle> inventory = new ArrayList<>();

    List<Vehicle> getVehiclesByPrice(float min, float max) {
        List<Vehicle> vehicles = new ArrayList<>();

        return vehicles;
    }
}
