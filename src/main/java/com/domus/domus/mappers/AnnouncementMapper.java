package com.domus.domus.mappers;

import com.domus.domus.dto.AnnouncementDto;
import com.domus.domus.entities.Announcement;

import java.time.LocalDate;

public class AnnouncementMapper {
    public AnnouncementDto toDto (Announcement announcement) {
        AnnouncementDto dto = new AnnouncementDto();
        dto.setId(announcement.getId());
        dto.setTitle(announcement.getTitle());
        dto.setMessage(announcement.getMessage());
        dto.setDatePosted(announcement.getDatePosted());

        return dto;
    }

    public Announcement toEntity (AnnouncementDto dto) {
        Announcement announcement = new Announcement();
        announcement.setId(dto.getId());
        announcement.setTitle(dto.getTitle());
        announcement.setMessage(dto.getMessage());
        announcement.setDatePosted(dto.getDatePosted());

        return announcement;
    }
}
