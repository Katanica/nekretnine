package com.example.backend.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.UUID;

@RestController
public class FileUploadController {
    private final Path storagePath = Paths.get("uploaded-images");

    public FileUploadController() throws IOException {
        if (Files.notExists(storagePath)) {
            Files.createDirectories(storagePath);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is missing");
        }

        String originalName = file.getOriginalFilename();
        if (originalName == null || !originalName.toLowerCase(Locale.ROOT).matches(".*\\.(jpg|jpeg|png|gif)$")) {
            return ResponseEntity.badRequest().body("Only image files are allowed");
        }

        // Strip path segments to avoid storing file names with embedded paths
        String cleanName = Paths.get(originalName).getFileName().toString();
        String storedName = UUID.randomUUID() + "_" + cleanName;

        Path targetFile = storagePath.resolve(storedName).normalize();

        if (!targetFile.startsWith(storagePath)) {
            return ResponseEntity.badRequest().body("Invalid file path");
        }

        try (InputStream stream = file.getInputStream()) {
            Files.copy(stream, targetFile, StandardCopyOption.REPLACE_EXISTING);
            return ResponseEntity.ok("Image stored as " + storedName);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Upload failed");
        }
    }
}
