// package com.college.cms.service;

// public class FileStorageService {
    
// }
// package com.college.cms.service;

// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;

// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;

// @Service
// public class FileStorageService {
    
//     private final Path rootLocation = Paths.get("uploads");
    
//     public void init() {
//         try {
//             Files.createDirectories(rootLocation);
//         } catch (IOException e) {
//             throw new RuntimeException("Could not initialize storage", e);
//         }
//     }
    
//     public String store(MultipartFile file) {
//         try {
//             String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//             Files.copy(file.getInputStream(), this.rootLocation.resolve(filename));
//             return filename;
//         } catch (Exception e) {
//             throw new RuntimeException("Failed to store file", e);
//         }
//     }
// }