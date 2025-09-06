package com.college.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.cms.model.CourseApplication;

@Repository
public interface CourseApplicationRepository extends JpaRepository<CourseApplication, Long> {
    List<CourseApplication> findByApplicantId(Long applicantId);
}