package com.domus.domus.dto;

import lombok.Data;

import java.time.*;

@Data
public class AnnouncementDto {
    private Long id;
    private String title;
    private String message;
    private LocalDate datePosted;
}
