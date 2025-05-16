package com.domus.domus.mappers;

import com.domus.domus.dto.ApartmentDto;
import com.domus.domus.entities.Apartment;

public class ApartmentMapper {
    public ApartmentDto toDto (Apartment apartment) {
        ApartmentDto dto = new ApartmentDto();
        dto.setId(apartment.getId());
        dto.setNumber(apartment.getNumber());
        dto.setOwnerName(apartment.getOwnerName());
        dto.setHasPaid(apartment.getHasPaid());

        return dto;
    }

    public Apartment toEntity (ApartmentDto dto) {
        Apartment apartment = new Apartment();
        apartment.setId(dto.getId());
        apartment.setNumber(dto.getNumber());
        apartment.setOwnerName(dto.getOwnerName());
        apartment.setHasPaid(dto.getHasPaid());

        return apartment;
    }

}
