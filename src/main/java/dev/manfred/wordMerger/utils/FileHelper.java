package dev.manfred.wordMerger.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

public class FileHelper {
    public static List<String> convertFileToList(MultipartFile file) throws IOException {
        return new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.toList());
    }

    /**
     * source: https://howtodoinjava.com/java/io/sha-md5-file-checksum-hash/
     */
    public static String getMD5CheckSum(MultipartFile file) {
        try {
            //Use MD5 algorithm
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");

            //Get the checksum
            return getFileChecksum(md5Digest, file);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * source: https://howtodoinjava.com/java/io/sha-md5-file-checksum-hash/
     */
    private static String getFileChecksum(MessageDigest digest, MultipartFile file) throws IOException {
        InputStream fis = file.getInputStream();
        byte[] byteArray = new byte[1024];
        int bytesCount;

        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }
        fis.close();

        byte[] bytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
