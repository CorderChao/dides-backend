package com.college.cms.service.Impl;

import com.college.cms.dto.requestDTOs.CourseSelectionRequest;
import com.college.cms.dto.requestDTOs.DeclarationDTOs;
import com.college.cms.dto.requestDTOs.EducationRequest;
import com.college.cms.dto.requestDTOs.NextOfKinRequest;
import com.college.cms.dto.requestDTOs.PersonalInfoRequest;
import com.college.cms.dto.requestDTOs.SponsorshipRequest;


import com.college.cms.model.Student;
import com.college.cms.repository.StudentRepository;
import com.college.cms.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    @Autowired
    private final StudentRepository studentRepository;
    private final ObjectMapper objectMapper;

    @Override
    public Student saveStep1(PersonalInfoRequest dto) {
        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setPlaceOfBirth(dto.getPlaceOfBirth());
        student.setCitizenship(dto.getCitizenship());
        student.setPermanentAddress(dto.getPermanentAddress());
        student.setContactAddress(dto.getContactAddress());
        student.setEmail(dto.getEmail());
        student.setMobile(dto.getMobile());
        student.setMaritalStatus(dto.getMaritalStatus().name());
        student.setSex(dto.getSex().name());
        student.setResidenceCategory(dto.getResidenceCategory());
        student.setHasDisability(dto.getHasDisability());
        student.setDisabilityType(dto.getDisabilityType() != null ? List.of(dto.getDisabilityType()) : null);
        student.setDisabilityDetails(dto.getDisabilityDetails());
        return studentRepository.save(student);
    }

    @Override
    public Student saveStep2(Long studentId, NextOfKinRequest dto) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        student.setNextOfKinName(dto.getName());
        student.setNextOfKinMobile(dto.getMobile());
        student.setNextOfKinRelationship(dto.getRelationship());
        student.setNextOfKinResidence(dto.getResidence());
        return studentRepository.save(student);
    }

    @Override
    public Student saveStep3(Long studentId, CourseSelectionRequest dto) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        student.setSelectedCourses(List.of(dto.getSelectedCourses()));
        student.setSelectedVetaCourses(List.of(dto.getSelectedVetaCourses()));
        student.setSelectedFreeCourses(List.of(dto.getSelectedFreeCourses()));
        return studentRepository.save(student);
    }

    @Override
    public Student saveStep4(Long studentId, EducationRequest dto) throws JsonProcessingException {
        Student student = studentRepository.findById(studentId).orElseThrow();
        student.setPrimarySchoolJson(objectMapper.writeValueAsString(dto.getPrimarySchool()));
        student.setSecondarySchoolJson(objectMapper.writeValueAsString(dto.getSecondarySchool()));
        student.setAdvancedEducationJson(objectMapper.writeValueAsString(dto.getAdvancedEducation()));
        student.setOtherQualificationsJson(objectMapper.writeValueAsString(dto.getOtherQualifications()));
        return studentRepository.save(student);
    }

    @Override
    public Student saveStep5(Long studentId, SponsorshipRequest dto) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        student.setSponsorshipType(dto.getType());
        student.setSponsorName(dto.getName());
        student.setSponsorAddress(dto.getAddress());
        student.setSponsorMobile(dto.getMobile());
        student.setSponsorEmail(dto.getEmail());
        return studentRepository.save(student);
    }

    @Override
    public Student saveStep6(Long studentId, DeclarationDTOs dto) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        student.setAgreeToTerms(dto.isAgreeToTerms());
        return studentRepository.save(student);
    }
}
