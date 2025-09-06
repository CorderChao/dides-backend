package com.college.cms.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationFormDTO {
    @NotBlank(message = "Last name is required")
    private String lastName;
    
    @NotBlank(message = "First name is required")
    private String firstName;
    
    private String middleName;
    
    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;
    
    @NotBlank(message = "Sex is required")
    private String sex;
    
    @NotBlank(message = "Place of birth is required")
    private String placeOfBirth;
    
    @NotBlank(message = "Citizenship is required")
    private String citizenship;
    
    private String maritalStatus;
    
    @NotBlank(message = "Permanent address is required")
    private String permanentAddress;
    
    private String contactAddress;
    
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;
    
    @NotBlank(message = "Mobile number is required")
    private String mobileNumber;
    
    private Boolean hasDisability;
    private String residenceCategory;
    
    @NotEmpty(message = "At least one education background is required")
    private List<EducationDTO> educationBackgrounds;
    
    @NotEmpty(message = "At least one course selection is required")
    private List<CourseSelectionDTO> courseSelections;
    
    @NotNull(message = "Sponsorship information is required")
    private SponsorshipDTO sponsorship;
    
    @AssertTrue(message = "You must agree to the terms")
    private Boolean agreementAccepted;
}