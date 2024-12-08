package com.ojeommeo.user

import com.ojeommeo.domain.user.dto.LoginRequest
import com.ojeommeo.domain.user.dto.SignUpRequest

fun testSignInRequest(id: Long?) =
    SignUpRequest(
        id = null,
        username = "han",
        nickname = "한해피",
        password = "ojeommeo123!",
    )

fun testLoginRequest() =
    LoginRequest(
        username = "han",
        password = "ojeommeo123!",
    )
