package com.ojeommeo.domain.auth.controller

import com.ojeommeo.domain.auth.dto.SignUpRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class AuthController {
   @PostMapping("/api/auth/signup")
   fun signup(@Valid @RequestBody signUpRequest: SignUpRequest) =
       ResponseEntity.created(URI.create("/users/1")).body(signUpRequest)
}
