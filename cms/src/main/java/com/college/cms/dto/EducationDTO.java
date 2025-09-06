package com.college.cms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducationDTO {
    @NotBlank(message = "Qualification type is required")
    private String qualificationType;
    
    private Integer startYear;
    private Integer endYear;
    private String indexNumber;
    private String examinationAuthority;
    private String division;
    private String schoolName;
    private String examinationCenter;
    private String country;
}