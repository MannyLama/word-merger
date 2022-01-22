package dev.manfred.wordMerger.utils;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class FileHelperTest {

    @Test
    public void convertFileToListTest() {
        String contentType = "text/plain";
        MockMultipartFile file = new MockMultipartFile("smallfile.txt", "smallfile.txt", contentType, "Hello\nTest\n3".getBytes(StandardCharsets.UTF_8));
        List<String> converted = null;

        try {
            converted = FileHelper.convertFileToList(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(converted);
        assertEquals(3, converted.size());
        assertTrue(converted.contains("Hello"));
        assertTrue(converted.contains("Test"));
        assertTrue(converted.contains("3"));
    }

    @Test
    public void MD5ChecksumTest() {
        String contentType = "text/plain";
        MockMultipartFile file = new MockMultipartFile("smallfile.txt", "smallfile.txt", contentType, "I'm a unique file.".getBytes(StandardCharsets.UTF_8));

        String checkSum = FileHelper.getMD5CheckSum(file);

        assertNotNull(checkSum);
        assertEquals("e04bff075606efa4a6c67448982838e1", checkSum);
    }
}
