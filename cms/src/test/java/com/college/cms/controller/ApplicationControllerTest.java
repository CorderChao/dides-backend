package com.college.cms.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.college.cms.controllers.ApplicationController;
import com.college.cms.dto.ApplicationFormDTO;
import com.college.cms.dto.CourseSelectionDTO;
import com.college.cms.dto.EducationDTO;
import com.college.cms.dto.SponsorshipDTO;
import com.college.cms.model.Applicant;
import com.college.cms.service.ApplicantService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ApplicationController.class)
public class ApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private ApplicantService applicantService;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateApplication() throws Exception {
        // Create test data
        ApplicationFormDTO applicationForm = createTestApplicationForm();
        
        // Mock the service behavior
        when(applicantService.existsByEmail("test@example.com")).thenReturn(false);
        when(applicantService.saveApplicant(any(Applicant.class))).thenReturn(new Applicant());

        // Perform the test
        mockMvc.perform(post("/api/applications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(applicationForm)))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnBadRequestWhenEmailExists() throws Exception {
        // Create test data
        ApplicationFormDTO applicationForm = createTestApplicationForm();
        
        // Mock the service behavior
        when(applicantService.existsByEmail("test@example.com")).thenReturn(true);

        // Perform the test
        mockMvc.perform(post("/api/applications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(applicationForm)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email already exists"));
    }

    @Test
    public void shouldGetApplicationById() throws Exception {
        Applicant applicant = new Applicant();
        applicant.setId(1L);
        applicant.setEmail("test@example.com");
        
        when(applicantService.getApplicantById(1L)).thenReturn(Optional.of(applicant));

        mockMvc.perform(get("/api/applications/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    public void shouldReturnNotFoundWhenApplicationDoesNotExist() throws Exception {
        when(applicantService.getApplicantById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/applications/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateApplication() throws Exception {
        ApplicationFormDTO applicationForm = createTestApplicationForm();
        Applicant existingApplicant = new Applicant();
        existingApplicant.setId(1L);
        
        when(applicantService.getApplicantById(1L)).thenReturn(Optional.of(existingApplicant));
        when(applicantService.saveApplicant(any(Applicant.class))).thenReturn(existingApplicant);

        mockMvc.perform(put("/api/applications/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(applicationForm)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNotFoundWhenUpdatingNonExistentApplication() throws Exception {
        ApplicationFormDTO applicationForm = createTestApplicationForm();
        
        when(applicantService.getApplicantById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/applications/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(applicationForm)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteApplication() throws Exception {
        Applicant applicant = new Applicant();
        applicant.setId(1L);
        
        when(applicantService.getApplicantById(1L)).thenReturn(Optional.of(applicant));

        mockMvc.perform(delete("/api/applications/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnNotFoundWhenDeletingNonExistentApplication() throws Exception {
        when(applicantService.getApplicantById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/applications/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnBadRequestForInvalidApplicationData() throws Exception {
        // Create invalid application data (missing required fields)
        ApplicationFormDTO invalidApplication = new ApplicationFormDTO();
        // Don't set any required fields
        
        mockMvc.perform(post("/api/applications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidApplication)))
                .andExpect(status().isBadRequest());
    }

    private ApplicationFormDTO createTestApplicationForm() {
        ApplicationFormDTO applicationForm = new ApplicationFormDTO();
        applicationForm.setLastName("Doe");
        applicationForm.setFirstName("John");
        applicationForm.setMiddleName("Michael");
        applicationForm.setDateOfBirth(LocalDate.of(1995, 5, 15));
        applicationForm.setSex("Male");
        applicationForm.setPlaceOfBirth("Dodoma");
        applicationForm.setCitizenship("Tanzanian");
        applicationForm.setMaritalStatus("Single");
        applicationForm.setPermanentAddress("123 Main St, Dodoma");
        applicationForm.setContactAddress("123 Main St, Dodoma");
        applicationForm.setEmail("test@example.com");
        applicationForm.setMobileNumber("+255123456789");
        applicationForm.setHasDisability(false);
        applicationForm.setResidenceCategory("Urban");
        applicationForm.setAgreementAccepted(true);

        // Create education background
        EducationDTO education = new EducationDTO();
        education.setQualificationType("CSEE");
        education.setStartYear(2010);
        education.setEndYear(2014);
        education.setIndexNumber("S1234");
        education.setExaminationAuthority("NECTA");
        education.setDivision("Division I");
        education.setSchoolName("Secondary School");
        education.setExaminationCenter("Dodoma");
        education.setCountry("Tanzania");

        applicationForm.setEducationBackgrounds(Collections.singletonList(education));

        // Create course selection
        CourseSelectionDTO course = new CourseSelectionDTO();
        course.setCourseName("Certificate in Community Development");
        course.setCourseType("Certificate");
        course.setNature("Long course");
        course.setIsSelected(true);

        applicationForm.setCourseSelections(Collections.singletonList(course));

        // Create sponsorship
        SponsorshipDTO sponsorship = new SponsorshipDTO();
        sponsorship.setType("Self-Sponsored");
        sponsorship.setSponsorFullName("John Doe");
        sponsorship.setSponsorPostalAddress("P.O. Box 123, Dodoma");
        sponsorship.setSponsorMobileNumber("+255987654321");
        sponsorship.setSponsorEmail("sponsor@example.com");

        applicationForm.setSponsorship(sponsorship);

        return applicationForm;
    }
}