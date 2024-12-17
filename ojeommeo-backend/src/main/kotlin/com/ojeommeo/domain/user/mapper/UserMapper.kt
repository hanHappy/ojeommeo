package com.ojeommeo.domain.user.mapper

import com.ojeommeo.domain.auth.dto.LoginResponse
import com.ojeommeo.domain.user.entity.User

fun User.toLoginResponse(token: String) =
    LoginResponse(
        username = this.username,
        nickname = this.nickname,
        token = token,
    )
