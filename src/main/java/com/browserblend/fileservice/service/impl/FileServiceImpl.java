package com.browserblend.fileservice.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.browserblend.fileservice.extension.FileAlreadyExistsException;
import com.browserblend.fileservice.model.FileMarker;
import com.browserblend.fileservice.repository.FileMarkerRepository;
import com.browserblend.fileservice.service.FileService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
public class FileServiceImpl implements FileService {
    private final FileMarkerRepository repository;
    private final AmazonS3 amazonS3;
    private final String bucketName = "browserblend-files";
    public FileServiceImpl(FileMarkerRepository repository, AmazonS3 amazonS3) {
        this.repository = repository;
        this.amazonS3 = amazonS3;
    }
    @Override
    @Transactional
    public FileMarker upload(Long userId, MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        File file = new File(fileName);
        Optional<FileMarker> fileExists = repository.findByUserAndName(userId, fileName);
        if(fileExists.isPresent()) {
            throw new FileAlreadyExistsException(fileName);
        }
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(multipartFile.getBytes());
        } catch(IOException e) {
            e.printStackTrace();
        }
        PutObjectRequest request = new PutObjectRequest(
                bucketName,
                "%d/%s".formatted(userId, multipartFile.getOriginalFilename()),
                file
        );
        amazonS3.putObject(request);
        String resourceUrl = String.valueOf(amazonS3.getUrl(bucketName, fileName));
        return repository.save(
                new FileMarker(resourceUrl, "action", userId, fileName)
        );
    }

    @Override
    public FileMarker deleteByUserAndId(Long userId, Long fileId) {
        Optional<FileMarker> optionalFileMarker = repository.findById(fileId);
        FileMarker fileMarker = optionalFileMarker.orElseThrow(() -> new EntityNotFoundException("File for user %d and file %d not found".formatted(userId, fileId)));
        String key = userId + "/" + fileMarker.getName();
        DeleteObjectRequest request = new DeleteObjectRequest(
                bucketName,
                key
        );
        amazonS3.deleteObject(request);
        repository.deleteByUserAndId(userId, fileId);
        return fileMarker;
    }

    @Override
    public FileMarker getByUserAndId(Long userId, Long fileId) {
        Optional<FileMarker> optionalMarker = repository.findByUserAndId(userId, fileId);
        if(optionalMarker.isPresent()) {
            return optionalMarker.get();
        }
        throw new EntityNotFoundException("FileMarker for user %d and id %d does not exist".formatted(userId, fileId));
    }

    @Override
    public List<FileMarker> getAllByUser(Long userId) {
        return repository.findAllByUser(userId);
    }

    @Override
    @Transactional
    public FileMarker updateByUserAndId(Long userId, Long fileId, MultipartFile multipartFile) {
        Optional<FileMarker> optionalFileMarker = repository.findByUserAndId(userId, fileId);
        optionalFileMarker.orElseThrow(() ->
                new EntityNotFoundException("FileMarker for userId %d and fileId %d does not exist".formatted(userId, fileId))
        );
        deleteByUserAndId(userId, fileId);
        return upload(userId, multipartFile);
    }
}
