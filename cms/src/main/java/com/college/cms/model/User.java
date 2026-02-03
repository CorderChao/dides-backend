// package com.college.cms.model;

// import java.util.Collection;
// import java.util.List;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToOne;
// import jakarta.persistence.Table;
// import jakarta.persistence.UniqueConstraint;
// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.Size;
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Entity
// @Table(name = "users", uniqueConstraints = {
//         @UniqueConstraint(columnNames = "username"),
//         @UniqueConstraint(columnNames = "email")
// })
// public class User implements UserDetails {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @NotBlank(message = "Username is mandatory")
//     @Size(max = 20)
//     private String username;

//     @NotBlank(message = "Email is mandatory")
//     @Size(max = 50)
//     @Email
//     private String email;

//     @NotBlank(message = "Password is mandatory")
//     @Size(max = 120)
//     private String password;

//     @Enumerated(EnumType.STRING)
//     private Role role; // ROLE_STUDENT, ROLE_STAFF, ROLE_ADMIN

//     // Link to the Student profile if the user is a student
//     @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//     private Student student;

//     // Implement UserDetails interface methods
//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return List.of(new SimpleGrantedAuthority(role.name()));
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }
    
//     public enum Role {
//         ROLE_STUDENT,
//         ROLE_STAFF,
//         ROLE_ADMIN
//     }
// }