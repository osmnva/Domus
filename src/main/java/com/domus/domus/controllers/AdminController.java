package com.domus.domus.controllers;

import com.domus.domus.dto.AnnouncementRequestDTO;
import com.domus.domus.entities.Announcement;
import com.domus.domus.entities.Payment;
import com.domus.domus.entities.UserEntity;
import com.domus.domus.services.AnnouncementService;
import com.domus.domus.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
        UserEntity admin = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return announcementService.createAnnouncement(dto, admin);
    }

    @GetMapping("/announcements/{id}/debtors")
    public List<Payment> getDebtors(@PathVariable Long id) {
        return paymentService.getDebtors(id);
    }
}