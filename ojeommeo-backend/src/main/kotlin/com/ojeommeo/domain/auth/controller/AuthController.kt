package com.ojeommeo.domain.auth.controller

import com.ojeommeo.domain.auth.dto.LoginRequest
import com.ojeommeo.domain.auth.dto.LoginResponse
import com.ojeommeo.domain.auth.dto.SignUpRequest
import com.ojeommeo.domain.auth.mapper.toUserEntity
import com.ojeommeo.domain.auth.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authService: AuthService,
    private val passwordEncoder: PasswordEncoder,
) {
    @PostMapping("/api/auth/sign-up")
    fun signUp(
        @Valid @RequestBody signUpRequest: SignUpRequest,
    ): ResponseEntity<Unit> {
        authService.signUp(signUpRequest.toUserEntity(passwordEncoder))
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PostMapping("/api/auth/login")
    fun login(
        @Valid @RequestBody loginRequest: LoginRequest,
    ): ResponseEntity<LoginResponse> = ResponseEntity.ok(authService.login(loginRequest))
}
