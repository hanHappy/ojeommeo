package com.ojeommeo.domain.user.controller

import com.ojeommeo.domain.user.dto.LoginRequest
import com.ojeommeo.domain.user.dto.SignUpRequest
import com.ojeommeo.domain.user.mapper.toUserEntity
import com.ojeommeo.domain.user.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
) {
    @PostMapping("/api/users")
    fun signUp(
        @Valid @RequestBody signUpRequest: SignUpRequest,
    ): ResponseEntity<Void> {
        userService.signUp(signUpRequest.toUserEntity(passwordEncoder))
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PostMapping("/api/users/login")
    fun login(
        @Valid @RequestBody loginRequest: LoginRequest,
    ): ResponseEntity<String> = ResponseEntity.ok(userService.login(loginRequest))
}
