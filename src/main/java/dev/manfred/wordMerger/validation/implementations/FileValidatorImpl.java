package dev.manfred.wordMerger.validation.implementations;

import dev.manfred.wordMerger.validation.FileValidator;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileValidatorImpl implements FileValidator {
    /**
     * 26.214.000 bytes corresponds to 25 MB.
     */
    private final long MAX_FILE_SIZE = 26_214_400L;

    @Override
    public boolean validateFile(MultipartFile file) {
        return file.getSize() < MAX_FILE_SIZE;
    }
}
