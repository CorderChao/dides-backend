package com.college.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.college.cms.model.Applicant;
import com.college.cms.repository.ApplicantRepository;

@Service
@Transactional
public class StudentService {
    
    private final ApplicantRepository applicantRepository;
    
    @Autowired
    public StudentService(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }
    
    // Get all students with pagination
    public Page<Applicant> getAllStudents(Pageable pageable) {
        return applicantRepository.findAll(pageable);
    }
    
    public List<Applicant> getAllStudents() {
        return applicantRepository.findAll();
    }
    
    public Optional<Applicant> getStudentById(Long id) {
        return applicantRepository.findById(id);
    }
    
    public Applicant saveStudent(Applicant student) {
        return applicantRepository.save(student);
    }
    
    public void deleteStudent(Long id) {
        applicantRepository.deleteById(id);
    }
    
    // Bulk delete students
    public void deleteStudents(List<Long> ids) {
        applicantRepository.deleteAllById(ids);
    }
    
    // Get students by course name
    public List<Applicant> getStudentsByCourse(String courseName) {
        return applicantRepository.findAll().stream()
            .filter(applicant -> applicant.getCourseApplications() != null &&
                    applicant.getCourseApplications().stream()
                        .anyMatch(course -> courseName.equals(course.getCourseName()) && 
                                           Boolean.TRUE.equals(course.getIsSelected())))
            .collect(Collectors.toList());
    }
    
    // Search students by various criteria
    public List<Applicant> searchStudents(String firstName, String lastName, String email, String courseName) {
        return applicantRepository.findAll().stream()
            .filter(applicant -> {
                boolean matches = true;
                
                if (firstName != null && !firstName.isEmpty()) {
                    matches = matches && applicant.getFirstName().toLowerCase().contains(firstName.toLowerCase());
                }
                
                if (lastName != null && !lastName.isEmpty()) {
                    matches = matches && applicant.getLastName().toLowerCase().contains(lastName.toLowerCase());
                }
                
                if (email != null && !email.isEmpty()) {
                    matches = matches && applicant.getEmail().toLowerCase().contains(email.toLowerCase());
                }
                
                if (courseName != null && !courseName.isEmpty() && applicant.getCourseApplications() != null) {
                    matches = matches && applicant.getCourseApplications().stream()
                        .anyMatch(course -> courseName.equalsIgnoreCase(course.getCourseName()) && 
                                           Boolean.TRUE.equals(course.getIsSelected()));
                }
                
                return matches;
            })
            .collect(Collectors.toList());
    }
    
    // Get student statistics
    public Map<String, Object> getStudentStatistics() {
        Map<String, Object> stats = new HashMap<>();
        
        List<Applicant> allStudents = applicantRepository.findAll();
        
        // Total students
        stats.put("totalStudents", allStudents.size());
        
        // Students by course
        Map<String, Long> studentsByCourse = allStudents.stream()
            .filter(applicant -> applicant.getCourseApplications() != null)
            .flatMap(applicant -> applicant.getCourseApplications().stream()
                .filter(course -> Boolean.TRUE.equals(course.getIsSelected())))
            .collect(Collectors.groupingBy(
                course -> course.getCourseName() != null ? course.getCourseName() : "Unknown",
                Collectors.counting()
            ));
        stats.put("studentsByCourse", studentsByCourse);
        
        // Students with disabilities
        long studentsWithDisabilities = allStudents.stream()
            .filter(applicant -> Boolean.TRUE.equals(applicant.getHasDisability()))
            .count();
        stats.put("studentsWithDisabilities", studentsWithDisabilities);
        
        return stats;
    }
    
    // Find student by email
    public Optional<Applicant> getStudentByEmail(String email) {
        return applicantRepository.findByEmail(email);
    }
    
    // Check if email exists
    public boolean existsByEmail(String email) {
        return applicantRepository.existsByEmail(email);
    }
    
    // Get students by sponsorship type
    public List<Applicant> getStudentsBySponsorshipType(String sponsorshipType) {
        return applicantRepository.findAll().stream()
            .filter(applicant -> applicant.getSponsorship() != null &&
                    sponsorshipType.equalsIgnoreCase(applicant.getSponsorship().getType()))
            .collect(Collectors.toList());
    }
}