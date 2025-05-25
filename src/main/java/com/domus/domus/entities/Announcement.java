package com.domus.domus.entities;

import com.domus.domus.entities.enums.AnnouncementType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "announcements")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private AnnouncementType type;

    @Column(nullable = false)
    private Double requiredAmount;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserEntity createdBy;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime deadline;
}