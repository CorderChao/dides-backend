package com.college.cms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "education_backgrounds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EducationBackground {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Qualification type is required")
    @Column(name = "qualification_type", nullable = false)
    private String qualificationType;
    
    @Column(name = "start_year")
    private Integer startYear;
    
    @Column(name = "end_year")
    private Integer endYear;
    
    @Column(name = "index_number")
    private String indexNumber;
    
    @Column(name = "examination_authority")
    private String examinationAuthority;
    
    private String division;
    
    @Column(name = "school_name")
    private String schoolName;
    
    @Column(name = "examination_center")
    private String examinationCenter;
    
    private String country;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id", nullable = false)
    private Applicant applicant;
}