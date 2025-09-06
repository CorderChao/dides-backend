package com.college.cms.dto;

import java.time.LocalDate;
import java.util.List;

import com.college.cms.model.Applicant;
import com.college.cms.model.CourseApplication;
import com.college.cms.model.EducationBackground;
import com.college.cms.model.Sponsorship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate dateOfBirth;
    private String sex;
    private String placeOfBirth;
    private String citizenship;
    private String maritalStatus;
    private String permanentAddress;
    private String contactAddress;
    private String email;
    private String mobileNumber;
    private Boolean hasDisability;
    private String residenceCategory;
    private List<EducationBackground> educationBackgrounds;
    private List<CourseApplication> courseApplications;
    private Sponsorship sponsorship;
    private Boolean agreementAccepted;
    private LocalDate createdAt;
    
    // You can add conversion method from Applicant entity
    public static StudentResponseDTO fromApplicant(Applicant applicant) {
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(applicant.getId());
        dto.setLastName(applicant.getLastName());
        dto.setFirstName(applicant.getFirstName());
        dto.setMiddleName(applicant.getMiddleName());
        dto.setDateOfBirth(applicant.getDateOfBirth());
        dto.setSex(applicant.getSex());
        dto.setPlaceOfBirth(applicant.getPlaceOfBirth());
        dto.setCitizenship(applicant.getCitizenship());
        dto.setMaritalStatus(applicant.getMaritalStatus());
        dto.setPermanentAddress(applicant.getPermanentAddress());
        dto.setContactAddress(applicant.getContactAddress());
        dto.setEmail(applicant.getEmail());
        dto.setMobileNumber(applicant.getMobileNumber());
        dto.setHasDisability(applicant.getHasDisability());
        dto.setResidenceCategory(applicant.getResidenceCategory());
        dto.setEducationBackgrounds(applicant.getEducationBackgrounds());
        dto.setCourseApplications(applicant.getCourseApplications());
        dto.setSponsorship(applicant.getSponsorship());
        dto.setAgreementAccepted(applicant.getAgreementAccepted());
        dto.setCreatedAt(applicant.getCreatedAt());
        return dto;
    }
}