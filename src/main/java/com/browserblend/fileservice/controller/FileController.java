package com.browserblend.fileservice.controller;

import com.browserblend.fileservice.model.FileMarker;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.browserblend.fileservice.service.FileService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("files")
public class FileController {
    private final FileService service;
    public FileController(FileService service) {
        this.service = service;
    }
    @PostMapping(value = "{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FileMarker upload(@PathVariable Long userId, @RequestPart(value="file") MultipartFile multipartFile) {
        return service.upload(userId, multipartFile);
    }

    @GetMapping("{userId}/{fileId}")
    public FileMarker getByUserAndId(@PathVariable Long userId, @PathVariable Long fileId) {
        return service.getByUserAndId(userId, fileId);
    }
    @GetMapping("{userId}")
    public List<FileMarker> getAllByUser(@PathVariable Long userId) {
        return service.getAllByUser(userId);
    }
    @DeleteMapping("{userId}/{fileId}")
    public FileMarker deleteByUserAndId(@PathVariable Long userId, @PathVariable Long fileId) {
        return service.deleteByUserAndId(userId, fileId);
    }
    @PutMapping(value = "{userId}/{fileId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FileMarker updateByUserAndId(@PathVariable Long userId, @PathVariable Long fileId, @RequestPart(name = "file") MultipartFile multipartFile) {
        return service.updateByUserAndId(userId, fileId, multipartFile);
    }
}
