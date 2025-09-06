package com.college.cms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.college.cms.model.Applicant;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Optional<Applicant> findByEmail(String email);
    boolean existsByEmail(String email);
    
    // Find students by first name containing (case-insensitive)
    List<Applicant> findByFirstNameContainingIgnoreCase(String firstName);
    
    // Find students by last name containing (case-insensitive)
    List<Applicant> findByLastNameContainingIgnoreCase(String lastName);
    
    // Find students by email containing (case-insensitive)
    List<Applicant> findByEmailContainingIgnoreCase(String email);
    
    // Custom query to find students by course name
    @Query("SELECT a FROM Applicant a JOIN a.courseApplications c WHERE LOWER(c.courseName) = LOWER(:courseName) AND c.isSelected = true")
    List<Applicant> findByCourseName(@Param("courseName") String courseName);
    
    // Custom query to find students with pagination
    @Query("SELECT a FROM Applicant a WHERE LOWER(a.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(a.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(a.email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Applicant> searchStudents(@Param("searchTerm") String searchTerm, Pageable pageable);
    
    // Count students by course
    @Query("SELECT c.courseName, COUNT(a) FROM Applicant a JOIN a.courseApplications c WHERE c.isSelected = true GROUP BY c.courseName")
    List<Object[]> countStudentsByCourse();
}