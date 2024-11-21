package com.example.bajajapi.controller;

import com.example.bajajapi.model.PostRequest;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/bfhl")
@CrossOrigin(origins = "https://radiant-palmier-d832c4.netlify.app/")
public class ApiController {

    @GetMapping
    public String handleGet() {
        return "{\"operation_code\": 1}";
    }

    @PostMapping
    public Map<String, Object> handlePost(@RequestBody PostRequest request) {
        Map<String, Object> response = new HashMap<>();

        // Separate numbers and alphabets
        List<String> numbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        for (String item : request.getData()) {
            if (item.matches("\\d+")) {
                numbers.add(item);
            } else if (item.matches("[a-zA-Z]")) {
                alphabets.add(item);
            }
        }

        // Find the highest lowercase alphabet
        String highestLowercase = alphabets.stream()
                .filter(ch -> ch.matches("[a-z]"))  // Keep only lowercase alphabets
                .max(String::compareTo)             // Find the maximum alphabet
                .orElse("");                        // Default to empty if no lowercase exists

        // Check if any prime number exists in the numbers list
        boolean isPrimeFound = numbers.stream()
                .mapToInt(Integer::parseInt)       // Convert strings to integers
                .anyMatch(this::isPrime);          // Use helper method to check for prime

        // Handle Base64-encoded file (fileB64)
        if (request.getFileB64() != null && !request.getFileB64().isEmpty()) {
            try {
                // Decode Base64 string into bytes
                byte[] decodedFile = Base64.getDecoder().decode(request.getFileB64());

                // Validate and return file details
                response.put("file_valid", true);
                response.put("file_size_kb", decodedFile.length / 1024.0);  // File size in KB
                response.put("file_mime_type", "application/octet-stream");  // Example MIME type
            } catch (IllegalArgumentException e) {
                // If decoding fails, mark file as invalid
                response.put("file_valid", false);
            }
        } else {
            // If no file or empty file, mark it as invalid
            response.put("file_valid", false);
        }

        // Set other response fields
        response.put("is_success", true);
        response.put("numbers", numbers);
        response.put("alphabets", alphabets);
        response.put("highest_lowercase_alphabet", highestLowercase);
        response.put("is_prime_found", isPrimeFound);
        response.put("user_id", "john_doe_17091999");
        response.put("email", "john@xyz.com");
        response.put("roll_number", "ABCD123");

        return response;
    }

    // Helper method to check if a number is prime
    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
