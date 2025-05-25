package com.domus.domus.services;

import com.domus.domus.dto.PaymentRequestDTO;
import com.domus.domus.entities.*;
import com.domus.domus.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final AnnouncementService announcementService;

    public Payment createPayment(PaymentRequestDTO dto, UserEntity user) {
        Announcement announcement = announcementService.getAnnouncementById(dto.getAnnouncementId());

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setAnnouncement(announcement);
        payment.setAmount(dto.getAmount());
        payment.setDueDate(announcement.getDeadline());
        payment.setIsPaid(true);
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    public List<Payment> getDebtors(Long announcementId) {
        Announcement announcement = announcementService.getAnnouncementById(announcementId);
        return paymentRepository.findByAnnouncementAndIsPaidFalse(announcement);
    }

    public List<Payment> getUserPayments(UserEntity user) {
        return paymentRepository.findByUser(user);
    }
}