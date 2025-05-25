package com.domus.domus.controllers;

import com.domus.domus.dto.PaymentRequestDTO;
import com.domus.domus.entities.Payment;
import com.domus.domus.entities.UserEntity;
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

    @PostMapping("/payments")
    public Payment createPayment(@RequestBody PaymentRequestDTO dto) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return paymentService.createPayment(dto, user);
    }

    @GetMapping("/payments")
    public List<Payment> getMyPayments() {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return paymentService.getUserPayments(user);
    }
}