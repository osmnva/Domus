package com.domus.domus.services;

import com.domus.domus.dto.AnnouncementRequestDTO;
import com.domus.domus.entities.Announcement;
import com.domus.domus.entities.UserEntity;
import com.domus.domus.entities.enums.AnnouncementType;
import com.domus.domus.repositories.AnnouncementRepository;
import com.domus.domus.repositories.PaymentRepository;
import com.domus.domus.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AnnouncementRepository announcementRepo;
    private final PaymentRepository paymentRepo;
    private final UserRepository userRepo;

    public Announcement createPaymentAnnouncement(AnnouncementRequestDTO dto, UserEntity admin) {
        Announcement announcement = new Announcement();
        announcement.setType(AnnouncementType.PAYMENT);
        announcement.setTitle(dto.getTitle());
        announcement.setContent(dto.getContent());
        announcement.setRequiredAmount(dto.getRequiredAmount());
        announcement.setDeadline(dto.getDeadline());

        return announcementRepo.save(announcement);
    }

//    public List<UserEntity> getDebtors(Long announcementId) {
//        Announcement announcement = announcementRepo.findById(announcementId)
//                .orElseThrow(() -> new RuntimeException("Announcement not found"));
//
//        return userRepo.findAll().stream()
//                .filter(user -> paymentRepo.findByUserAndAnnouncement(user, announcement)
//                        .map(payment -> !payment.getIsPaid() && LocalDateTime.now().isAfter(payment.getDueDate()))
//                        .orElse(true))
//                .collect(Collectors.toList());
//    }
}