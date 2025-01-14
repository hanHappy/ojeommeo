package com.ojeommeo.auth

import com.ojeommeo.domain.auth.dto.LoginRequest
import com.ojeommeo.domain.auth.dto.LoginResponse
import com.ojeommeo.domain.auth.dto.SignUpRequest

fun testSignUpRequest() =
    SignUpRequest(
        username = "han",
        nickname = "한해피",
        password = "ojeommeo123!",
    )

fun testLoginRequest() =
    LoginRequest(
        username = "han",
        password = "ojeommeo123!",
    )

fun testLoginResponse() =
    LoginResponse(
        id = 999L,
        username = "han",
        nickname = "한상면",
        token = "token",
    )
