package com.college.cms.dto.requestDTOs;


import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducationRequest {

    private SchoolDTO primarySchool;
    private SchoolDTO secondarySchool;
    private SchoolDTO advancedEducation;
    private List<OtherQualificationDTO> otherQualifications;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SchoolDTO {
        private String schoolName;
        private String fromYear;
        private String toYear;
        private String indexNumber;
        private String authority;
        private String division;
        private String country;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OtherQualificationDTO {
        private String institute;
        private String fromYear;
        private String toYear;
        private String award;
    }
}
