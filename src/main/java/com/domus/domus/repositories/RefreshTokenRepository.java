package com.domus.domus.repositories;

import com.domus.domus.entities.RefreshToken;
import com.domus.domus.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUser(UserEntity user);
    void deleteByUser(UserEntity user);
}
