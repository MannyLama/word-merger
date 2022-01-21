package dev.manfred.wordMerger.validation;

import dev.manfred.wordMerger.validation.implementations.FileValidatorImpl;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationTest {

    private final FileValidator fileValidator = new FileValidatorImpl();

    @Test
    public void fileValidationTest() {
        String contentType = "text/plain";

        MockMultipartFile largeFile = new MockMultipartFile("largefile.txt", "largefile.txt", contentType, new byte[1024 * 1024 * 30]); //30MB
        MockMultipartFile smallFile = new MockMultipartFile("smallfile.txt", "smallfile.txt", contentType, new byte[1024 * 1024]); //1MB

        assertFalse(fileValidator.validateFile(largeFile));
        assertTrue(fileValidator.validateFile(smallFile));
    }
}
