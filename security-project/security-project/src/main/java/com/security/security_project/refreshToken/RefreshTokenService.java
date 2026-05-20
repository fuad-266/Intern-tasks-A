package com.security.security_project.refreshToken;

import com.security.security_project.entity.RefreshToken;
import com.security.security_project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken createRefreshToken(User user) {

        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .expiryDate(LocalDateTime.now().plusDays(7))
                .user(user)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    // ADD THIS NEW METHOD
    public RefreshToken verifyRefreshToken(String token) {

        RefreshToken refreshToken = refreshTokenRepository
                .findByToken(token)
                .orElseThrow(() ->
                        new RuntimeException("Refresh token not found")
                );

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {

            refreshTokenRepository.delete(refreshToken);

            throw new RuntimeException("Refresh token expired");
        }

        return refreshToken;
    }
}