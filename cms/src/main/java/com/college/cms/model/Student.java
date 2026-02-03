package com.college.cms.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Step 1: Personal Info
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private String citizenship;
    private String permanentAddress;
    private String contactAddress;
    private String email;
    private String mobile;
    private String maritalStatus;
    private String sex;
    private String residenceCategory;
    private Boolean hasDisability;
    @ElementCollection
    private List<String> disabilityType;
    private String disabilityDetails;

    // Step 2: Next of Kin
    private String nextOfKinName;
    private String nextOfKinMobile;
    private String nextOfKinRelationship;
    private String nextOfKinResidence;

    // Step 3: Course Selection
    @ElementCollection
    private List<Long> selectedCourses;
    @ElementCollection
    private List<Long> selectedVetaCourses;
    @ElementCollection
    private List<Long> selectedFreeCourses;

    // Step 4: Education Background
    // For simplicity, storing as JSON strings or separate entity can be used
    private String primarySchoolJson;
    private String secondarySchoolJson;
    private String advancedEducationJson;
    private String otherQualificationsJson;

    // Step 5: Sponsorship
    private String sponsorshipType;
    private String sponsorName;
    private String sponsorAddress;
    private String sponsorMobile;
    private String sponsorEmail;

    // Step 6: Declaration
    private Boolean agreeToTerms;
}
