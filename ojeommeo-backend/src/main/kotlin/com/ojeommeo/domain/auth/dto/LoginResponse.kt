package com.ojeommeo.domain.auth.dto

class LoginResponse(
    val id: Long,
    val username: String,
    val nickname: String,
    val token: String,
)
