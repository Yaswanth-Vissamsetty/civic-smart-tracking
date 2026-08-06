package com.civic.smarttracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CivicSmartTrackingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CivicSmartTrackingApplication.class, args);
        System.out.println("\n=======================================================");
        System.out.println("  Civic Smart Tracking System Started Successfully!");
        System.out.println("  Access Application at: http://localhost:8080");
        System.out.println("  Access H2 Console at:  http://localhost:8080/h2-console");
        System.out.println("  Default Admin Login:   admin@civic.gov.in / admin123");
        System.out.println("=======================================================\n");
    }
}
