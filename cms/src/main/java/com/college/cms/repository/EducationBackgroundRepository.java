package com.college.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.cms.model.EducationBackground;

@Repository
public interface EducationBackgroundRepository extends JpaRepository<EducationBackground, Long> {
    List<EducationBackground> findByApplicantId(Long applicantId);
}