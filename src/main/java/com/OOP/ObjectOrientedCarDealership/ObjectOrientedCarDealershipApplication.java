package com.OOP.ObjectOrientedCarDealership;

import com.OOP.ObjectOrientedCarDealership.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ObjectOrientedCarDealershipApplication implements CommandLineRunner {

	@Autowired
	private VehicleRepository vehicleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ObjectOrientedCarDealershipApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println(vehicleRepository.getAllVehicles());
	}
}
