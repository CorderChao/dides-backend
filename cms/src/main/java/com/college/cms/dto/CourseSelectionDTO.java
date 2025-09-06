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
public class CourseSelectionDTO {
    @NotBlank(message = "Course name is required")
    private String courseName;
    
    private String courseType;
    private String nature;
    private Boolean isSelected;
}