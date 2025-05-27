package com.domus.domus.dto;

import com.domus.domus.entities.Announcement;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponseDTO {
    private Long id;
    private UserSummaryDTO user;
    private Announcement announcement;
    private Double amount;
    private Boolean isPaid;
    private LocalDateTime paymentDate;
    private LocalDateTime dueDate;
}
