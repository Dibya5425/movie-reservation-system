package com.movie.reservation.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String uploadPoster(MultipartFile file);
}