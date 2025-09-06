package com.college.cms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sponsorships")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Sponsorship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Sponsorship type is required")
    @Column(name = "type", nullable = false)
    private String type;
    
    @Column(name = "sponsor_full_name")
    private String sponsorFullName;
    
    @Column(name = "sponsor_postal_address", length = 500)
    private String sponsorPostalAddress;
    
    @Column(name = "sponsor_mobile_number")
    private String sponsorMobileNumber;
    
    @Column(name = "sponsor_email")
    private String sponsorEmail;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id", nullable = false)
    private Applicant applicant;
}