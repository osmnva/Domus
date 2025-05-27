package com.domus.domus.dto;

import com.domus.domus.entities.enums.AnnouncementType;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AnnouncementRequestDTO {
    private String title;
    private String content;
    private AnnouncementType type;
    private Double requiredAmount;
    private LocalDateTime deadline;
}