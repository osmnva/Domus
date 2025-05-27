package com.domus.domus.services;

import com.domus.domus.dto.PaymentRequestDTO;
import com.domus.domus.dto.PaymentResponseDTO;
import com.domus.domus.dto.UserSummaryDTO;
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

    public List<PaymentResponseDTO> getUserPayments(UserEntity user) {
        return paymentRepository.findByUser(user)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<PaymentResponseDTO> getPendingPayments(UserEntity user) {
        return paymentRepository.findByUserAndIsPaidFalse(user)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<PaymentResponseDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<PaymentResponseDTO> getPaymentsByAnnouncement(Long announcementId) {
        Announcement announcement = announcementRepository.findById(announcementId)
                .orElseThrow(() -> new RuntimeException("Announcement not found"));
        return paymentRepository.findByAnnouncement(announcement)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
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

    public PaymentResponseDTO toDto(Payment payment) {
        return PaymentResponseDTO.builder()
                .id(payment.getId())
                .user(new UserSummaryDTO(
                        payment.getUser().getId(),
                        payment.getUser().getUsername()
                ))
                .announcement(payment.getAnnouncement())
                .amount(payment.getAmount())
                .isPaid(payment.getIsPaid())
                .paymentDate(payment.getPaymentDate())
                .dueDate(payment.getDueDate())
                .build();
    }
}
