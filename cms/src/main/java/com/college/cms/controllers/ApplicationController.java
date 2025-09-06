package com.college.cms.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired; // Fixed import
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.cms.dto.ApplicationFormDTO;
import com.college.cms.dto.SponsorshipDTO;
import com.college.cms.model.Applicant;
import com.college.cms.model.CourseApplication;
import com.college.cms.model.EducationBackground;
import com.college.cms.model.Sponsorship;
import com.college.cms.service.ApplicantService;
import com.college.cms.service.EmailService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:4200")
public class ApplicationController {
    
    private final ApplicantService applicantService;
    private final EmailService emailService;
    
    @Autowired
    public ApplicationController(ApplicantService applicantService, EmailService emailService) {
        this.applicantService = applicantService;
        this.emailService = emailService;
    }
    
    @GetMapping
    public ResponseEntity<List<Applicant>> getAllApplications() {
        List<Applicant> applications = applicantService.getAllApplicants();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Applicant> getApplicationById(@PathVariable Long id) {
        Optional<Applicant> application = applicantService.getApplicantById(id);
        return application.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping
    public ResponseEntity<?> submitApplication(@Valid @RequestBody ApplicationFormDTO applicationFormDTO) {
        if (applicantService.existsByEmail(applicationFormDTO.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }
        
        Applicant applicant = convertToEntity(applicationFormDTO);
        Applicant savedApplicant = applicantService.saveApplicant(applicant);
        
        // Send confirmation email
        try {
            emailService.sendApplicationConfirmation(
                savedApplicant.getEmail(), 
                savedApplicant.getFirstName() + " " + savedApplicant.getLastName()
            );
        } catch (Exception e) {
            // Log email error but don't fail the application submission
            System.err.println("Failed to send confirmation email: " + e.getMessage());
        }
        
        return new ResponseEntity<>(savedApplicant, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Applicant> updateApplication(@PathVariable Long id, 
                                                     @Valid @RequestBody ApplicationFormDTO applicationFormDTO) {
        Optional<Applicant> existingApplication = applicantService.getApplicantById(id);
        
        if (existingApplication.isPresent()) {
            Applicant updatedApplicant = convertToEntity(applicationFormDTO);
            updatedApplicant.setId(id);
            Applicant savedApplicant = applicantService.saveApplicant(updatedApplicant);
            return new ResponseEntity<>(savedApplicant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        if (applicantService.getApplicantById(id).isPresent()) {
            applicantService.deleteApplicant(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    private Applicant convertToEntity(ApplicationFormDTO dto) {
        Applicant applicant = new Applicant();
        applicant.setLastName(dto.getLastName());
        applicant.setFirstName(dto.getFirstName());
        applicant.setMiddleName(dto.getMiddleName());
        applicant.setDateOfBirth(dto.getDateOfBirth());
        applicant.setSex(dto.getSex());
        applicant.setPlaceOfBirth(dto.getPlaceOfBirth());
        applicant.setCitizenship(dto.getCitizenship());
        applicant.setMaritalStatus(dto.getMaritalStatus());
        applicant.setPermanentAddress(dto.getPermanentAddress());
        applicant.setContactAddress(dto.getContactAddress());
        applicant.setEmail(dto.getEmail());
        applicant.setMobileNumber(dto.getMobileNumber());
        applicant.setHasDisability(dto.getHasDisability());
        applicant.setResidenceCategory(dto.getResidenceCategory());
        applicant.setAgreementAccepted(dto.getAgreementAccepted());
        
        // Convert education backgrounds
        if (dto.getEducationBackgrounds() != null) {
            List<EducationBackground> educationBackgrounds = dto.getEducationBackgrounds().stream()
                .map(eduDto -> {
                    EducationBackground edu = new EducationBackground();
                    edu.setQualificationType(eduDto.getQualificationType());
                    edu.setStartYear(eduDto.getStartYear());
                    edu.setEndYear(eduDto.getEndYear());
                    edu.setIndexNumber(eduDto.getIndexNumber());
                    edu.setExaminationAuthority(eduDto.getExaminationAuthority());
                    edu.setDivision(eduDto.getDivision());
                    edu.setSchoolName(eduDto.getSchoolName());
                    edu.setExaminationCenter(eduDto.getExaminationCenter());
                    edu.setCountry(eduDto.getCountry());
                    edu.setApplicant(applicant);
                    return edu;
                })
                .collect(Collectors.toList());
            applicant.setEducationBackgrounds(educationBackgrounds);
        }
        
        // Convert course applications - FIXED THIS SECTION
        if (dto.getCourseSelections() != null) {
            List<CourseApplication> courseApplications = dto.getCourseSelections().stream()
                .map(courseDto -> {
                    CourseApplication course = new CourseApplication();
                    course.setCourseName(courseDto.getCourseName());
                    course.setCourseType(courseDto.getCourseType());
                    course.setNature(courseDto.getNature());
                    course.setIsSelected(courseDto.getIsSelected());
                    course.setApplicant(applicant);
                    return course;
                })
                .collect(Collectors.toList());
            applicant.setCourseApplications(courseApplications);
        }
        
        // Convert sponsorship - FIXED THIS SECTION
        if (dto.getSponsorship() != null) {
            SponsorshipDTO sponsorDto = dto.getSponsorship(); // Fixed: SponsorshipDTO (not SponsorshipDto)
            Sponsorship sponsorship = new Sponsorship();
            sponsorship.setType(sponsorDto.getType());
            sponsorship.setSponsorFullName(sponsorDto.getSponsorFullName());
            sponsorship.setSponsorPostalAddress(sponsorDto.getSponsorPostalAddress());
            sponsorship.setSponsorMobileNumber(sponsorDto.getSponsorMobileNumber());
            sponsorship.setSponsorEmail(sponsorDto.getSponsorEmail());
            sponsorship.setApplicant(applicant);
            applicant.setSponsorship(sponsorship);
        }
        
        return applicant;
    }
}