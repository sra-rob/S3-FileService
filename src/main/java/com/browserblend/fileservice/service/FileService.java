package com.browserblend.fileservice.service;

import com.browserblend.fileservice.model.FileMarker;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    FileMarker upload(Long userId, MultipartFile multipartFile);
    FileMarker deleteByUserAndId(Long userId, Long id);
    FileMarker getByUserAndId(Long userId, Long fileId);
    List<FileMarker> getAllByUser(Long userId);
    FileMarker updateByUserAndId(Long userId, Long fileId, MultipartFile multipartFile);
}
