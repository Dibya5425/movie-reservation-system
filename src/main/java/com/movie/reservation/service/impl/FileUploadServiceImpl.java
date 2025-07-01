package com.movie.reservation.service.impl;

import com.movie.reservation.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    private final String uploadDir = "uploads/";

    @Override
    public String uploadPoster(MultipartFile file) {
        try {
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(uploadDir + filename);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
            String fileUrl = "/uploads/" + filename;
            log.info("Poster uploaded successfully: {}", fileUrl);
            return fileUrl;
        } catch (IOException e) {
            log.error("Error uploading file", e);
            throw new RuntimeException("Failed to store file", e);
        }
    }
}