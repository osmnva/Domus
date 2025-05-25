package com.domus.domus.services;

import com.domus.domus.dto.AnnouncementRequestDTO;
import com.domus.domus.entities.Announcement;
import com.domus.domus.entities.UserEntity;
import com.domus.domus.repositories.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public Announcement createAnnouncement(AnnouncementRequestDTO dto, UserEntity creator) {
        Announcement announcement = new Announcement();
        announcement.setTitle(dto.getTitle());
        announcement.setContent(dto.getContent());
        announcement.setType(dto.getType());
        announcement.setRequiredAmount(dto.getRequiredAmount());
        announcement.setDeadline(dto.getDeadline());
        announcement.setCreatedBy(creator);
        return announcementRepository.save(announcement);
    }

    public Announcement getAnnouncementById(Long id) {
        return announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found"));
    }
}