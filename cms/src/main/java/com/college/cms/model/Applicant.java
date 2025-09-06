package com.college.cms.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "applicants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Last name is required")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @NotBlank(message = "First name is required")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @Column(name = "middle_name")
    private String middleName;
    
    @NotNull(message = "Date of birth is required")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;
    
    @NotBlank(message = "Sex is required")
    @Column(nullable = false)
    private String sex;
    
    @NotBlank(message = "Place of birth is required")
    @Column(name = "place_of_birth", nullable = false)
    private String placeOfBirth;
    
    @NotBlank(message = "Citizenship is required")
    @Column(nullable = false)
    private String citizenship;
    
    @Column(name = "marital_status")
    private String maritalStatus;
    
    @NotBlank(message = "Permanent address is required")
    @Column(name = "permanent_address", nullable = false, length = 500)
    private String permanentAddress;
    
    @Column(name = "contact_address", length = 500)
    private String contactAddress;
    
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;
    
    @NotBlank(message = "Mobile number is required")
    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;
    
    @Column(name = "has_disability")
    private Boolean hasDisability;
    
    @Column(name = "residence_category")
    private String residenceCategory;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicant", fetch = FetchType.LAZY)
    private List<EducationBackground> educationBackgrounds;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicant", fetch = FetchType.LAZY)
    private List<CourseApplication> courseApplications;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "applicant", fetch = FetchType.LAZY)
    private Sponsorship sponsorship;
    
    @Column(name = "agreement_accepted")
    private Boolean agreementAccepted;
    
    @Column(name = "created_at")
    private LocalDate createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }
}