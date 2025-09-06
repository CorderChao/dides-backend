package com.college.cms.controllers;

import com.college.cms.model.Applicant;
import com.college.cms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Get all students with pagination and sorting
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        try {
            Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
            
            Page<Applicant> studentPage = studentService.getAllStudents(pageable);
            
            Map<String, Object> response = new HashMap<>();
            response.put("students", studentPage.getContent());
            response.put("currentPage", studentPage.getNumber());
            response.put("totalItems", studentPage.getTotalElements());
            response.put("totalPages", studentPage.getTotalPages());
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Applicant> getStudentById(@PathVariable Long id) {
        Optional<Applicant> student = studentService.getStudentById(id);
        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get students by course name
    @GetMapping("/course/{courseName}")
    public ResponseEntity<List<Applicant>> getStudentsByCourse(@PathVariable String courseName) {
        try {
            List<Applicant> students = studentService.getStudentsByCourse(courseName);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Search students by various criteria
    @GetMapping("/search")
    public ResponseEntity<List<Applicant>> searchStudents(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String courseName) {
        
        try {
            List<Applicant> students = studentService.searchStudents(firstName, lastName, email, courseName);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update student information
    @PutMapping("/{id}")
    public ResponseEntity<Applicant> updateStudent(@PathVariable Long id, @Valid @RequestBody Applicant studentDetails) {
        Optional<Applicant> existingStudent = studentService.getStudentById(id);
        
        if (existingStudent.isPresent()) {
            Applicant student = existingStudent.get();
            
            // Update fields
            student.setFirstName(studentDetails.getFirstName());
            student.setLastName(studentDetails.getLastName());
            student.setMiddleName(studentDetails.getMiddleName());
            student.setEmail(studentDetails.getEmail());
            student.setMobileNumber(studentDetails.getMobileNumber());
            student.setPermanentAddress(studentDetails.getPermanentAddress());
            student.setContactAddress(studentDetails.getContactAddress());
            student.setHasDisability(studentDetails.getHasDisability());
            student.setResidenceCategory(studentDetails.getResidenceCategory());
            
            Applicant updatedStudent = studentService.saveStudent(student);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Bulk delete students
    @DeleteMapping("/bulk")
    public ResponseEntity<HttpStatus> deleteStudents(@RequestBody List<Long> ids) {
        try {
            studentService.deleteStudents(ids);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get student statistics
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStudentStatistics() {
        try {
            Map<String, Object> stats = studentService.getStudentStatistics();
            return new ResponseEntity<>(stats, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update student status (e.g., active, graduated, dropped out)
    @PatchMapping("/{id}/status")
    public ResponseEntity<Applicant> updateStudentStatus(@PathVariable Long id, @RequestBody Map<String, String> status) {
        Optional<Applicant> existingStudent = studentService.getStudentById(id);
        
        if (existingStudent.isPresent() && status.containsKey("status")) {
            Applicant student = existingStudent.get();
            // You might want to add a status field to your Applicant entity
            // For now, we'll use a generic approach
            Applicant updatedStudent = studentService.saveStudent(student);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}