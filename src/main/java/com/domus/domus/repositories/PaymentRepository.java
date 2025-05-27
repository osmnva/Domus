package com.domus.domus.repositories;

import com.domus.domus.entities.Announcement;
import com.domus.domus.entities.Payment;
import com.domus.domus.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUser(UserEntity user);
    List<Payment> findByUserAndIsPaidFalse(UserEntity user);
    List<Payment> findByAnnouncement(Announcement announcement);
    boolean existsByUserAndAnnouncementAndIsPaidTrue(UserEntity user, Announcement announcement);
}