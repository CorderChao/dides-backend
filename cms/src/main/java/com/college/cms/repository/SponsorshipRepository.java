package com.college.cms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.cms.model.Sponsorship;

@Repository
public interface SponsorshipRepository extends JpaRepository<Sponsorship, Long> {
    Optional<Sponsorship> findByApplicantId(Long applicantId);
}