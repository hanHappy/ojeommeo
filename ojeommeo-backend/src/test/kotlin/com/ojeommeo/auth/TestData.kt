package com.ojeommeo.auth

import com.ojeommeo.domain.user.entity.User

fun testSignInRequest (id: Long) = User(
    id = id,
    username = "han",
    nickname = "한해피",
    password = "ojeommeo123!"
)
