package com.browserblend.fileservice.extension;

public class FileAlreadyExistsException extends RuntimeException{
    public FileAlreadyExistsException(String fileName) {
        super("File with name %s already exists".formatted(fileName));
    }
}
