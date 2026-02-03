// package com.college.cms.controllers;

// import com.college.cms.dto.requestDTOs.LoginRequest;
// import com.college.cms.dto.requestDTOs.SignupRequest;
// import com.college.cms.dto.responseDTOs.JwtResponse;
// import com.college.cms.dto.responseDTOs.MessageResponse;
// import com.college.cms.exception.DuplicateResourceException;
// import com.college.cms.model.User;
// import com.college.cms.security.JwtService;
// import com.college.cms.security.UserDetailsImpl;
// import com.college.cms.service.UserService;
// import jakarta.validation.Valid;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.stream.Collectors;

// @CrossOrigin(origins = "*", maxAge = 3600)
// @RestController
// @RequestMapping("/api/auth")
// @RequiredArgsConstructor
// public class AuthController {

//     private final AuthenticationManager authenticationManager;
//     private final UserService userService;
//     private final JwtService jwtService;

//     @PostMapping("/signin")
//     public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//         // Authenticate user
//         Authentication authentication = authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

//         SecurityContextHolder.getContext().setAuthentication(authentication);
        
//         // Generate JWT token
//         String jwt = jwtService.generateJwtToken(authentication);
        
//         // Get user details
        
//         UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//         List<String> roles = userDetails.getAuthorities().stream()
//                 .map(GrantedAuthority::getAuthority)
//                 .collect(Collectors.toList());

//         // Return response with JWT token
//         return ResponseEntity.ok(new JwtResponse(
//                 jwt,
//                 userDetails.getId(),
//                 userDetails.getUsername(),
//                 userDetails.getEmail(),
//                 roles.get(0) // Get the first role
//         ));
//     }

//     @PostMapping("/signup")
//     public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
//         // Check if username already exists
//         if (userService.existsByUsername(signUpRequest.getUsername())) {
//             throw new DuplicateResourceException("Username is already taken!");
//         }

//         // Check if email already exists
//         if (userService.existsByEmail(signUpRequest.getEmail())) {
//             throw new DuplicateResourceException("Email is already in use!");
//         }

//         // Create new user account
        
//         userService.createUser(signUpRequest);

//         return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//     }

//     @PostMapping("/signup/student")
//     public ResponseEntity<?> registerStudent(@Valid @RequestBody SignupRequest signUpRequest) {
//         // Force the role to be STUDENT for student registration
//         signUpRequest.setRole(User.Role.ROLE_STUDENT);
//         return registerUser(signUpRequest);
//     }
// }