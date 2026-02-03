/*package com.college.cms.dto.requestDTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentApplicationRequest {
    // Personal Information (Page 1)
    @NotBlank private String lastName;
    @NotNull private Boolean hasDisability;
    @NotBlank private String firstName;
    @Past private LocalDate dateOfBirth;
    @NotBlank private String gender;
    @NotBlank private String placeOfBirth;
    @NotBlank private String citizenship;
    private String maritalStatus;
    @NotBlank private String permanentAddress;
    @NotBlank private String contactAddress;
    @NotBlank @Email private String email;
    @NotBlank private String mobileNumber;
    @NotBlank private String residenceCategory;

    // Next of Kin (Page 2)
    @NotBlank private String nextOfKinName;
    @NotBlank private String nextOfKinMobile;
    @NotBlank private String nextOfKinRelationship;
    @NotBlank private String nextOfKinResidence;

    // Courses (Page 3)
    @Data
    public class CourseSelectionRequest {
    private Long applicationId;
    private String firstChoiceCourse;
    private String secondChoiceCourse;
    private String shortCourseChoice;
}

    // Sponsorship (Page 5)
    private String sponsorshipType;
    private String sponsorFullName;
    private String sponsorPostalAddress;
    private String sponsorMobileNumber;
    private String sponsorEmailAddress;

    // Education (Page 4 - Simplified)
    private String secondarySchoolName;
    private String secondarySchoolIndexNumber;
}*/