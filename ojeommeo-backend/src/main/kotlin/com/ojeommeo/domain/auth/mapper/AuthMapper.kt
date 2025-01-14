package com.ojeommeo.domain.auth.mapper

import com.ojeommeo.domain.auth.dto.SignUpRequest
import com.ojeommeo.domain.user.entity.User
import org.springframework.security.crypto.password.PasswordEncoder

fun SignUpRequest.toUserEntity(passwordEncoder: PasswordEncoder) =
    User(
        id = null,
        username = this.username,
        password = passwordEncoder.encode(this.password),
        nickname = this.nickname,
        profileImage = null,
        lastLoginAt = null,
        createdAt = null,
        updatedAt = null,
    )
