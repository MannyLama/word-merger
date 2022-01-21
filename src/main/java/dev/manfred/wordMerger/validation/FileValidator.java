package dev.manfred.wordMerger.validation;

import org.springframework.web.multipart.MultipartFile;

public interface FileValidator {
    boolean validateFile(MultipartFile file);
}
