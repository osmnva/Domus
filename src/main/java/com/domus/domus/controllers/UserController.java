package com.domus.domus.controllers;

import com.domus.domus.dto.PaymentRequestDTO;
import com.domus.domus.dto.PaymentResponseDTO;
import com.domus.domus.entities.Announcement;
import com.domus.domus.entities.UserEntity;
import com.domus.domus.services.AnnouncementService;
import com.domus.domus.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class UserController {

    private final PaymentService paymentService;
    private final AnnouncementService announcementService;

    @GetMapping("/announcements")
    public List<Announcement> getAllAnnouncements() {
        return announcementService.getAllAnnouncements();
    }

    @PostMapping("/payments")
    public PaymentResponseDTO createPayment(@RequestBody PaymentRequestDTO dto) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return paymentService.toDto(paymentService.createPayment(dto, user));
    }

    @GetMapping("/payments")
    public List<PaymentResponseDTO> getMyPayments() {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return paymentService.getUserPayments(user);
    }

    @GetMapping("/payments/pending")
    public List<PaymentResponseDTO> getPendingPayments() {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return paymentService.getPendingPayments(user);
    }
}