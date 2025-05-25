package com.domus.domus.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequestDTO {
    @NotNull
    private Long announcementId;

    @NotNull
    private Double amount;
}