// package com.college.cms.dto.requestDTOs;


// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.Size;
// import lombok.Getter;
// import lombok.Setter;


// @Getter
// @Setter
// public class SignupRequest {
//     @NotBlank(message = "Username is mandatory")
//     @Size(min = 3, max = 20)
//     private String username;

//     @NotBlank(message = "Email is mandatory")
//     @Size(max = 50)
//     @Email
//     private String email;

//     @NotBlank(message = "Password is mandatory")
//     @Size(min = 6, max = 40)
//     private String password;

//     private User.Role role; // This will typically be set to ROLE_STUDENT by default
// }
