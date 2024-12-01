package com.ojeommeo.domain.user.mapper

import com.ojeommeo.domain.user.dto.SignUpRequest
import com.ojeommeo.domain.user.entity.User

fun SignUpRequest.toUserEntity() = User(
    id = this.id,
    username = this.username,
    nickname = this.nickname,
    password = this.password,
)
