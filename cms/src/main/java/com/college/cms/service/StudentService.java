package com.college.cms.service;

import com.college.cms.dto.requestDTOs.PersonalInfoRequest;
import com.college.cms.dto.requestDTOs.NextOfKinRequest;
import com.college.cms.dto.requestDTOs.CourseSelectionRequest;
import com.college.cms.dto.requestDTOs.EducationRequest;
import com.college.cms.dto.requestDTOs.SponsorshipRequest;
import com.college.cms.dto.requestDTOs.DeclarationDTOs;
import com.college.cms.model.Student;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface StudentService {
    Student saveStep1(PersonalInfoRequest dto);
    Student saveStep2(Long studentId, NextOfKinRequest dto);
    Student saveStep3(Long studentId, CourseSelectionRequest dto);
    Student saveStep4(Long studentId, EducationRequest dto) throws JsonProcessingException;
    Student saveStep5(Long studentId, SponsorshipRequest dto);
    Student saveStep6(Long studentId, DeclarationDTOs dto);
}
