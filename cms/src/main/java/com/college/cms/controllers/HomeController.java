package com.college.cms.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Home", description = "Home controller for API information and health checks")
public class HomeController {

    @GetMapping("/")
    @Operation(summary = "Welcome endpoint", description = "Returns welcome message and API information")
    public ResponseEntity<Map<String, Object>> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to Dodoma Institute of Development and Entrepreneurship Studies (DIDES) API");
        response.put("version", "1.0.0");
        response.put("timestamp", LocalDateTime.now());
        response.put("documentation", "/swagger-ui.html");
        response.put("apiBase", "/api");
        response.put("contact", "dides@example.com");
        
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("applications", "/api/applications - Manage student applications");
        endpoints.put("students", "/api/students - Manage enrolled students");
        endpoints.put("courses", "/api/courses - Manage available courses");
        endpoints.put("health", "/health - System health check");
        
        response.put("endpoints", endpoints);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Returns system health status")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "College Management System");
        response.put("timestamp", LocalDateTime.now());
        response.put("version", "1.0.0");
        response.put("database", "Connected"); // You could add actual DB health check
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api")
    @Operation(summary = "API information", description = "Returns detailed API information")
    public ResponseEntity<Map<String, Object>> apiInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("name", "DDES College Management System API");
        response.put("description", "REST API for managing college applications, students, and courses");
        response.put("version", "1.0.0");
        response.put("swaggerUI", "/swagger-ui.html");
        response.put("openApiSpec", "/v3/api-docs");
        
        Map<String, String> mainEndpoints = new HashMap<>();
        mainEndpoints.put("Applications", "GET,POST,PUT,DELETE /api/applications");
        mainEndpoints.put("Students", "GET,PUT,DELETE /api/students");
        mainEndpoints.put("Courses", "GET,POST,PUT,DELETE /api/courses");
        
        response.put("mainEndpoints", mainEndpoints);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/info")
    @Operation(summary = "System information", description = "Returns system configuration information")
    public ResponseEntity<Map<String, Object>> systemInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("system", "College Management System");
        response.put("institution", "Dodoma Institute of Development and Entrepreneurship Studies (DDES)");
        response.put("version", "1.0.0");
        response.put("javaVersion", System.getProperty("java.version"));
        response.put("timestamp", LocalDateTime.now());
        
        return ResponseEntity.ok(response);
    }
}