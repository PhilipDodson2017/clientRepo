package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ApiClient {

    private final String BASE_URL = "http://localhost:8080/customers";
    private final RestTemplate restTemplate = new RestTemplate();

    

    // Fetch all customers
    public List<Customer> getAllCustomers() {

        try {
        ResponseEntity<Customer[]> response = restTemplate.getForEntity(BASE_URL, Customer[].class);
        return Arrays.asList(response.getBody());
        }
        catch (Exception e) {
            return new ArrayList<Customer>();
        }
        

    }

    // Create a new customer
    public Customer createCustomer(Customer customer) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Customer> request = new HttpEntity<>(customer, headers);
        return restTemplate.postForObject(BASE_URL, request, Customer.class);
    }

    // Update a customer
    public Customer updateCustomer(Long id, Customer customer) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Customer> request = new HttpEntity<>(customer, headers);
        ResponseEntity<Customer> response = restTemplate.exchange(BASE_URL + "/" + id, org.springframework.http.HttpMethod.PUT, request, Customer.class);
        return response.getBody();
    }

    // Delete a customer
    public void deleteCustomer(Long id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }
}