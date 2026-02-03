// package com.college.cms.service.Impl;

// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.college.cms.dto.requestDTOs.SignupRequest;
// import com.college.cms.model.User;
// import com.college.cms.repository.UserRepository;
// import com.college.cms.service.UserService;

// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class UserServiceImpl implements UserService {

//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;

//     @Override
//     public User createUser(SignupRequest signUpRequest) {
//         // Validate unique username/email
//         if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//             throw new IllegalArgumentException("Username is already taken");
//         }
//         if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//             throw new IllegalArgumentException("Email is already in use");
//         }

//         User user = new User();
//         user.setUsername(signUpRequest.getUsername());
//         user.setEmail(signUpRequest.getEmail());
//         user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

//         // Set default role safely
//         if (signUpRequest.getRole() == null) {
//             user.setRole(User.Role.ROLE_STUDENT);
//         } else if (signUpRequest.getRole().equals(User.Role.ROLE_ADMIN)) {
//             throw new IllegalArgumentException("Cannot assign ADMIN role on signup");
//         } else {
//             user.setRole(signUpRequest.getRole());
//         }

//         return userRepository.save(user);
//     }

//     @Override
//     public Boolean existsByUsername(String username) {
//         return userRepository.existsByUsername(username);
//     }

//     @Override
//     public Boolean existsByEmail(String email) {
//         return userRepository.existsByEmail(email);
//     }
// }
