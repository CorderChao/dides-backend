package com.college.cms.dto.requestDTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseSelectionRequest {
    @NotEmpty(message = "At least one course must be selected")
    private Long[] selectedCourses;
    private Long[] selectedVetaCourses;
    private Long[] selectedFreeCourses;
}
