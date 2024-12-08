package com.ojeommeo.security.repository

import com.ojeommeo.security.entity.JwtRefreshToken
import org.springframework.data.jpa.repository.JpaRepository

interface JwtRefreshTokenRepository : JpaRepository<JwtRefreshToken, Long> {
    fun findByUserId(userId: Long): JwtRefreshToken?
}
