package com.browserblend.fileservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "file_markers")
public class FileMarker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String resourceUrl;
    private String type;
    private Long userId;
    private String name;
    public FileMarker() {}
    public FileMarker(String resourceUrl, String type, Long userId, String name) {
        this.resourceUrl = resourceUrl;
        this.type = type;
        this.userId = userId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String uri) {
        this.resourceUrl = uri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FileMarker{" +
                "id=" + id +
                ", resourceUrl='" + resourceUrl + '\'' +
                ", type='" + type + '\'' +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}
