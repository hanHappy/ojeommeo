package com.ojeommeo.domain.user.controller

import com.ojeommeo.domain.user.dto.SignUpRequest
import com.ojeommeo.domain.user.mapper.toUserEntity
import com.ojeommeo.domain.user.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {

    @PostMapping("/api/users")
    fun signUp(@Valid @RequestBody signUpRequest: SignUpRequest): ResponseEntity<Void> {
        userService.signUp(signUpRequest.toUserEntity())
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}
