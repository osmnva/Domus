package com.domus.domus.controllers;

import com.domus.domus.entities.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.domus.domus.services.ApartmentService;

import java.util.List;

@RestController
@RequestMapping("/api/apartments")
public class ApartmentController {

    private final ApartmentService apartmentService;

    @Autowired
    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping
    public ResponseEntity<List<Apartment>> getAllApartments() {
        return ResponseEntity.ok(apartmentService.getAllApartments());
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<String> markAsPaid(@PathVariable Long id) {
        apartmentService.markAsPaid(id);
        return ResponseEntity.ok("Квартира №" + id + " отмечена как оплатившая.");
    }

    @GetMapping("/debtors")
    public ResponseEntity<List<Apartment>> getDebtors() {
        return ResponseEntity.ok(apartmentService.getDebtors());
    }

    @GetMapping("/mine")
    public ResponseEntity<Apartment> getMyApartment(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Apartment apartment = apartmentService.getMyApartment(username);
        return ResponseEntity.ok(apartment);
    }
}

