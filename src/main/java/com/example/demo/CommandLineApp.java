package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class CommandLineApp implements CommandLineRunner {

    @Autowired
    private ApiClient apiClient;

    public static void main(String[] args) {
        SpringApplication.run(CommandLineApp.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Customer API Client!");
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. List all customers");
            System.out.println("2. Create a new customer");
            System.out.println("3. Update a customer");
            System.out.println("4. Delete a customer");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    try {
                    System.out.println("Fetching all customers...");
                    apiClient.getAllCustomers().forEach(System.out::println);
                    }
                    catch (Exception e) {
                        System.out.println("Exception fetching customers...try adding one first");
                    }
                }
                case 2 -> {
                    Customer newCustomer = new Customer();
                    System.out.print("Enter first name: ");
                    newCustomer.setFirstName(scanner.nextLine());
                    System.out.print("Enter middle name: ");
                    newCustomer.setMiddleName(scanner.nextLine());
                    System.out.print("Enter last name: ");
                    newCustomer.setLastName(scanner.nextLine());
                    System.out.print("Enter email: ");
                    newCustomer.setEmailAddress(scanner.nextLine());
                    System.out.print("Enter phone number: ");
                    newCustomer.setPhoneNumber(scanner.nextLine());

                    Customer createdCustomer = apiClient.createCustomer(newCustomer);
                    System.out.println("Customer created: " + createdCustomer);
                }
                case 3 -> {
                    System.out.print("Enter customer ID to update: ");
                    Long updateId = scanner.nextLong();
                    scanner.nextLine(); // Consume newline

                    Customer updatedCustomer = new Customer();
                    System.out.print("Enter first name: ");
                    updatedCustomer.setFirstName(scanner.nextLine());
                    System.out.print("Enter middle name: ");
                    updatedCustomer.setMiddleName(scanner.nextLine());
                    System.out.print("Enter last name: ");
                    updatedCustomer.setLastName(scanner.nextLine());
                    System.out.print("Enter email: ");
                    updatedCustomer.setEmailAddress(scanner.nextLine());
                    System.out.print("Enter phone number: ");
                    updatedCustomer.setPhoneNumber(scanner.nextLine());

                    Customer result = apiClient.updateCustomer(updateId, updatedCustomer);
                    System.out.println("Customer updated: " + result);
                }
                case 4 -> {
                    System.out.print("Enter customer ID to delete: ");
                    Long deleteId = scanner.nextLong();
                    apiClient.deleteCustomer(deleteId);
                    System.out.println("Customer deleted.");
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}