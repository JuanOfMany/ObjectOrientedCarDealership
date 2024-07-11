package com.OOP.ObjectOrientedCarDealership;

import com.OOP.ObjectOrientedCarDealership.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserInterface implements CommandLineRunner {

//		System.out.println(vehicleRepository.getAllVehicles());

//		System.out.println(vehicleRepository.getVehiclesByPrice(29999, 40000))

    @Autowired
    VehicleRepository vehicleRepository ;

    public static void main(String[] args) {

    }

    @Override
    public void run(String... args) {
        System.out.println("What would you like to do?");
        System.out.println("1 - Find vehicles within a price range.");
        System.out.println("2 - Find vehicles by make / model.");
        System.out.println("3 - Find vehicles by year range.");
        System.out.println("4 - Find vehicles by color.");
        System.out.println("5 - Find vehicles by mileage range.");
        System.out.println("6 - Find vehicles by type (car, truck, SUV, van");
        System.out.println("7 - List ALL vehicles.");
        System.out.println("8 - Add a vehicle.");
        System.out.println("9 - Remove a vehicle.");
        System.out.println("Enter a number:");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        switch (userInput) {
            case "1":
                System.out.println("Enter a price range:");
                int lowestPrice = Integer.parseInt(scanner.nextLine());
                int highestPrice = Integer.parseInt(scanner.nextLine());
                System.out.println(vehicleRepository.getVehiclesByPrice(lowestPrice, highestPrice));
                break;
            case "2":
                System.out.println("Enter a make and model:");
                String make = scanner.nextLine();
                String model = scanner.nextLine();
                System.out.println(vehicleRepository.getVehicleByMakeModel(make, model));
                break;
            case "3":
                System.out.println("Enter a year range:");
                int lowestYear = Integer.parseInt(scanner.nextLine());
                int highestYear = Integer.parseInt(scanner.nextLine());
                System.out.println(vehicleRepository.getVehiclesByYearRange(lowestYear, highestYear));
                break;
            case "4":
                System.out.println("Enter a color:");
                String color = scanner.nextLine();
                System.out.println(vehicleRepository.getVehiclesByColor(color));
                break;
            case "5":
                System.out.println("Enter a range of miles:");
                int lowerMiles = Integer.parseInt(scanner.nextLine());
                int upperMiles = Integer.parseInt(scanner.nextLine());
                System.out.println(vehicleRepository.getVehiclesByMileRange(lowerMiles, upperMiles));
                break;
            case "6":
                System.out.println("Enter a type of vehicle:");
                break;
            case "7":
                vehicleRepository.getAllVehicles().forEach(System.out::println);
                break;
            case "8":
                System.out.println("Which vehicle do you want to add?");
                break;
            case "9":
                System.out.println("Enter vehicle Id to remove.");
                int idToRemove = Integer.parseInt(scanner.nextLine());
                System.out.println(vehicleRepository.removeVehicleById(idToRemove));
                break;
            case "99":
                System.out.println("Shutting down.");
                System.exit(0);
                break;
        }
    }
}
