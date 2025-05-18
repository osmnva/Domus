package com.domus.domus.repositories;

import com.domus.domus.entities.Apartment;
import com.domus.domus.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    Optional<Apartment> findByUser(User user);

}
