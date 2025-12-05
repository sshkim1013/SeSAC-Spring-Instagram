package com.example.instagram.service;

import com.example.instagram.exception.BusinessException;
import com.example.instagram.exception.ErrorCode;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Value("${file.upload-dir}")    // application.properties 설정
    private String uploadDir;

    private static final List<String> ALLOWED_EXTENSIONS =
        Arrays.asList(".jpg", ".jpeg", ".png", ".gif");

    @Override
    public String saveFile(MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) return null;

            String originalFilename = file.getOriginalFilename();
            String extension = getExtension(originalFilename);

            // 허용된 확장자가 아니라면 예외를 발생.
            if (!ALLOWED_EXTENSIONS.contains(extension.toLowerCase())) {
                // throw new RuntimeException("Invalid file extension");
                throw new BusinessException(ErrorCode.INVALID_FILE_TYPE);
            }

            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 새로운 경로 생성
            String savedFileName = UUID.randomUUID() + extension;

            Path filePath = uploadPath.resolve(savedFileName); //  ex) uploads/78132ghf-1789gjk.jpg
            Files.copy(file.getInputStream(), filePath);

            return savedFileName;

        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류 발생!");
        }
    }

    // 확장자 가져오기
    private String getExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }

        return filename.substring(filename.lastIndexOf("."));
    }

}
