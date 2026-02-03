package com.college.cms.dto.requestDTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NextOfKinRequest {
    @NotBlank(message = "Next of kin name is required")
    private String name;

    @NotBlank(message = "Mobile is required")
    private String mobile;

    @NotBlank(message = "Relationship is required")
    private String relationship;

    @NotBlank(message = "Residence is required")
    private String residence;
}
