package com.domus.domus.repositories;

import com.domus.domus.entities.Announcement;
import com.domus.domus.entities.Payment;
import com.domus.domus.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByAnnouncementAndIsPaidFalse(Announcement announcement);
    List<Payment> findByUser(UserEntity user);
    Optional<Payment> findByUserAndAnnouncement(UserEntity user, Announcement announcement);
}