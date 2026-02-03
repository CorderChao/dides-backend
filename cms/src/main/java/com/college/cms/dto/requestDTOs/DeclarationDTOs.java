package com.college.cms.dto.requestDTOs;

import jakarta.validation.constraints.AssertTrue;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeclarationDTOs {
    @AssertTrue(message = "You must agree to terms")
    private boolean agreeToTerms;
}
