package com.college.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.college.cms.model.Applicant;
import com.college.cms.repository.ApplicantRepository;

@Service
@Transactional
public class ApplicantService {
    
    private final ApplicantRepository applicantRepository;
    
    @Autowired
    public ApplicantService(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }
    
    public List<Applicant> getAllApplicants() {
        return applicantRepository.findAll();
    }
    
    public Optional<Applicant> getApplicantById(Long id) {
        return applicantRepository.findById(id);
    }
    
    public Applicant saveApplicant(Applicant applicant) {
        return applicantRepository.save(applicant);
    }
    
    public void deleteApplicant(Long id) {
        applicantRepository.deleteById(id);
    }
    
    public boolean existsByEmail(String email) {
        return applicantRepository.existsByEmail(email);
    }
    
    public Optional<Applicant> getApplicantByEmail(String email) {
        return applicantRepository.findByEmail(email);
    }
}