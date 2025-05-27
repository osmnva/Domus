package com.domus.domus.services;

import com.domus.domus.dto.PaymentRequestDTO;
import com.domus.domus.entities.Announcement;
import com.domus.domus.entities.Payment;
import com.domus.domus.entities.UserEntity;
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
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;

    public Payment createPayment(PaymentRequestDTO dto, UserEntity user) {
        Announcement announcement = announcementRepository.findById(dto.getAnnouncementId())
                .orElseThrow(() -> new RuntimeException("Announcement not found"));

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setAnnouncement(announcement);
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setIsPaid(true);

        return paymentRepository.save(payment);
    }

    public List<Payment> getUserPayments(UserEntity user) {
        return paymentRepository.findByUser(user);
    }

    public List<Payment> getPendingPayments(UserEntity user) {
        return paymentRepository.findByUserAndIsPaidFalse(user);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentsByAnnouncement(Long announcementId) {
        Announcement announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new RuntimeException("Announcement not found"));
        return paymentRepository.findByAnnouncement(announcement);
    }

    public List<UserEntity> getDebtors(Long announcementId) {
        Announcement announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new RuntimeException("Announcement not found"));

        LocalDateTime now = LocalDateTime.now();

        return userRepository.findAll().stream()
                .filter(user -> {
                    boolean hasPaid = paymentRepository.existsByUserAndAnnouncementAndIsPaidTrue(user, announcement);
                    boolean isPastDeadline = now.isAfter(announcement.getDeadline());
                    return !hasPaid && isPastDeadline;
                })
                .collect(Collectors.toList());
    }
}