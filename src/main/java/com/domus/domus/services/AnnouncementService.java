package com.domus.domus.services;

import com.domus.domus.dto.AnnouncementRequestDTO;
import com.domus.domus.entities.Announcement;
import com.domus.domus.repositories.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    public Announcement createAnnouncement(AnnouncementRequestDTO dto) {
        Announcement announcement = new Announcement();
        announcement.setTitle(dto.getTitle());
        announcement.setContent(dto.getContent());
        announcement.setType(dto.getType());
        announcement.setRequiredAmount(dto.getRequiredAmount());
        announcement.setDeadline(dto.getDeadline());

        return announcementRepository.save(announcement);
    }

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }
}