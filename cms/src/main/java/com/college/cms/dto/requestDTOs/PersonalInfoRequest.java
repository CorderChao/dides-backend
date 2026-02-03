package com.college.cms.dto.requestDTOs;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter

public class PersonalInfoRequest {
    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Place of birth is required")
    private String placeOfBirth;

    @NotBlank(message = "Citizenship is required")
    private String citizenship;

    @NotBlank(message = "Permanent address is required")
    private String permanentAddress;

    @NotBlank(message = "Contact address is required")
    private String contactAddress;

    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Mobile number is required")
    private String mobile;

    @NotNull(message = "Marital status is required")
    private MaritalStatus maritalStatus;

    @NotNull(message = "Gender is required")
    private Gender sex;

    @NotNull(message = "Residence category is required")
    private String residenceCategory;

    private Boolean hasDisability;
    private String[] disabilityType;
    private String disabilityDetails;
}
