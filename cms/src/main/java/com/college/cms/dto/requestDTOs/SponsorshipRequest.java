package com.college.cms.dto.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SponsorshipRequest {
    @NotBlank(message = "Sponsorship type is required")
    private String type;
    private String name;
    private String address;
    private String mobile;
    private String email;
}
