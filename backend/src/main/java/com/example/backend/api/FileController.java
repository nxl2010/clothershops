package com.example.backend.api;

import com.amazonaws.HttpMethod;
import com.example.backend.dto.response.S3DTO;
import com.example.backend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v2/s3")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/test")
    public ResponseEntity<String> test() {
        System.out.println("Hello");
        return ResponseEntity.ok("ok");
    }
    @PostMapping("/upload")
    public ResponseEntity<S3DTO> generateUrl(@RequestParam String extension) {
        UUID name = UUID.randomUUID();
        S3DTO s3DTO = new S3DTO();
        s3DTO.setName(String.valueOf(name));
        s3DTO.setLink(fileService.generatePreSignedUrl(
                name+"."+extension, HttpMethod.PUT));
        return ResponseEntity.ok(s3DTO);
    }

    @GetMapping("/getpdfurl")
    public ResponseEntity<String> getUrl(@RequestParam String filename) {
        return ResponseEntity.ok(fileService.generatePreSignedUrl(
                filename, HttpMethod.GET));
    }
}
