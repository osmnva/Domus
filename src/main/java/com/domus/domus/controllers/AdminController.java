package com.domus.domus.controllers;

import com.domus.domus.dto.AnnouncementRequestDTO;
import com.domus.domus.dto.PaymentResponseDTO;
import com.domus.domus.entities.Announcement;
import com.domus.domus.entities.UserEntity;
import com.domus.domus.services.AnnouncementService;
import com.domus.domus.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final AnnouncementService announcementService;
    private final PaymentService paymentService;

    @PostMapping("/announcements")
    public Announcement createAnnouncement(@RequestBody AnnouncementRequestDTO dto) {
        return announcementService.createAnnouncement(dto);
    }

    @GetMapping("/announcements")
    public List<Announcement> getAllAnnouncements() {
        return announcementService.getAllAnnouncements();
    }

    @GetMapping("/announcements/{id}/debtors")
    public List<UserEntity> getDebtors(@PathVariable Long id) {
        return paymentService.getDebtors(id);
    }

    @GetMapping("/payments")
    public List<PaymentResponseDTO> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/announcements/{id}/payments")
    public List<PaymentResponseDTO> getPaymentsByAnnouncement(@PathVariable Long id) {
        return paymentService.getPaymentsByAnnouncement(id);
    }
}
