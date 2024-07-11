package com.OOP.ObjectOrientedCarDealership;

import com.OOP.ObjectOrientedCarDealership.models.Dealership;
import com.OOP.ObjectOrientedCarDealership.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ObjectOrientedCarDealershipApplication{

	@Autowired
	UserInterface userInterface;

	public static void main(String[] args) {
		SpringApplication.run(ObjectOrientedCarDealershipApplication.class, args);
	}

}
