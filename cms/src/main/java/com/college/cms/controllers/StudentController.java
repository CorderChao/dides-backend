package com.college.cms.controllers;

import com.college.cms.dto.requestDTOs.PersonalInfoRequest;
import com.college.cms.dto.requestDTOs.NextOfKinRequest;
import com.college.cms.dto.requestDTOs.CourseSelectionRequest;
import com.college.cms.dto.requestDTOs.EducationRequest;
import com.college.cms.dto.requestDTOs.SponsorshipRequest;
import com.college.cms.dto.requestDTOs.DeclarationDTOs;



import com.college.cms.model.Student;
import com.college.cms.service.StudentService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
//@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class StudentController {
    
    @Autowired
    private final StudentService studentService;

    @PostMapping("/step1")
    public ResponseEntity<Long> submitStep1(@RequestBody @Valid PersonalInfoRequest dto) {
        Student student = studentService.saveStep1(dto);
        return ResponseEntity.ok(student.getId());
    }

    @PostMapping("/step2/{studentId}")
    public ResponseEntity<?> submitStep2(@PathVariable Long studentId,
                                         @RequestBody @Valid NextOfKinRequest dto) {
        studentService.saveStep2(studentId, dto);
        return ResponseEntity.ok("Step 2 valid");
    }

    @PostMapping("/step3/{studentId}")
    public ResponseEntity<?> submitStep3(@PathVariable Long studentId,
                                         @RequestBody @Valid CourseSelectionRequest dto) {
        studentService.saveStep3(studentId, dto);
        return ResponseEntity.ok("Step 3 valid");
    }

    @PostMapping("/step4/{studentId}")
    public ResponseEntity<?> submitStep4(@PathVariable Long studentId,
                                         @RequestBody @Valid EducationRequest dto) throws Exception {
        studentService.saveStep4(studentId, dto);
        return ResponseEntity.ok("Step 4 valid");
    }

    @PostMapping("/step5/{studentId}")
    public ResponseEntity<?> submitStep5(@PathVariable Long studentId,
                                         @RequestBody @Valid SponsorshipRequest dto) {
        studentService.saveStep5(studentId, dto);
        return ResponseEntity.ok("Step 5 valid");
    }

    @PostMapping("/step6/{studentId}")
    public ResponseEntity<?> submitStep6(@PathVariable Long studentId,
                                         @RequestBody @Valid DeclarationDTOs dto) {
        studentService.saveStep6(studentId, dto);
        return ResponseEntity.ok("Application submitted successfully!");
    }
}
