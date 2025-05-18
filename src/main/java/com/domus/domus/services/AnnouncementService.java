package com.domus.domus.services;

import com.domus.domus.entities.Announcement;
import com.domus.domus.repositories.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Autowired
    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    public Announcement createAnnouncement(String title, String message) {
        Announcement announcement = new Announcement(title, message, LocalDate.now());
        return announcementRepository.save(announcement);
    }
}
