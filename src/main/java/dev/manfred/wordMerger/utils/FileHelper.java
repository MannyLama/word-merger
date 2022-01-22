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
            MessageDigest md5Digest = null;

            md5Digest = MessageDigest.getInstance("MD5");


            //Get the checksum
            String checksum = null;

            checksum = getFileChecksum(md5Digest, file);


            //see checksum
            return checksum;
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    /**
     * source: https://howtodoinjava.com/java/io/sha-md5-file-checksum-hash/
     */
    private static String getFileChecksum(MessageDigest digest, MultipartFile file) throws IOException {
        //Get file input stream for reading the file content
        InputStream fis = file.getInputStream();

        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount;

        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }

        //close the stream; We don't need it now.
        fis.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }

        //return complete hash
        return sb.toString();
    }
}
