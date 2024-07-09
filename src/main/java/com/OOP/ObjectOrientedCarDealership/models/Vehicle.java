package com.OOP.ObjectOrientedCarDealership.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private int vehicleId;
    private String VIN;
    private boolean sold;
    private String color;
    private boolean electric;
    private int miles;
    private int doors;
    private boolean convertible;
    private String make;
    private String model;
    private int year;
    private float price;
    private String vehicleType;
}
