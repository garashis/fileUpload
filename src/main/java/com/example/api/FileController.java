package com.example.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public class FileController {
    private final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @PostMapping(value = "/file", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<String>> uploadAndExtractZipFile(@RequestPart MultipartFile file) {
        List<String> zipContent = new ArrayList<>();
        try (ZipInputStream stream = new ZipInputStream(file.getInputStream())) {
            LOGGER.info("Zip file: " + file.getOriginalFilename() + " has been opened");
            ZipEntry entry;
            while ((entry = stream.getNextEntry()) != null) {
                zipContent.add(entry.getName());
            }
        } catch (IOException ex) {
            LOGGER.info("Exception reading zip", ex);
        }
        return ResponseEntity.ok(zipContent);
    }
}
