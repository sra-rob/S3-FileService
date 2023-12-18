package com.browserblend.fileservice.repository;

import com.browserblend.fileservice.model.FileMarker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FileMarkerRepository extends JpaRepository<FileMarker, Long> {
    @Query("SELECT fm from FileMarker fm WHERE fm.userId = :userId AND fm.name = :name")
    Optional<FileMarker> findByUserAndName(Long userId, String name);
    @Query("SELECT fm FROM FileMarker fm WHERE fm.userId = :userId AND fm.id = :fileId")
    Optional<FileMarker> findByUserAndId(Long userId, Long fileId);
    @Query("SELECT fm FROM FileMarker fm WHERE fm.userId = :userId")
    List<FileMarker> findAllByUser(Long userId);
    @Transactional
    @Modifying
    @Query("DELETE FROM FileMarker fm WHERE fm.userId = :userId AND fm.id = :fileId")
    public void deleteByUserAndId(Long userId, Long fileId);
}
