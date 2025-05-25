package com.domus.domus.repositories;

import com.domus.domus.entities.Announcement;
import com.domus.domus.entities.UserEntity;
import com.domus.domus.entities.enums.AnnouncementType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByType(AnnouncementType type);
    List<Announcement> findByCreatedBy(UserEntity user);
}