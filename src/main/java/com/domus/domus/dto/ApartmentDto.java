package com.domus.domus.dto;

import lombok.Data;

@Data
public class ApartmentDto {
    private Long id;
    private Integer number;
    private String ownerName;
    private Boolean hasPaid = false;
}
