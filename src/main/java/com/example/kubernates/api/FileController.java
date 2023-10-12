package com.example.kubernates.api;

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
    @PostMapping(value = "/file", consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<String>> uploadFile(@RequestPart(value = "file") MultipartFile file) throws IOException {
        List<String> zipContent = new ArrayList<>();
        String originalFileName = file.getOriginalFilename();
        ZipInputStream zis = new ZipInputStream(file.getInputStream());
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            System.out.println(zipEntry.getName());
            zipContent.add(zipEntry.getName());
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
        return ResponseEntity.ok(zipContent);
    }
}
