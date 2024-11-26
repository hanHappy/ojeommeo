package com.ojeommeo.exception

class JwtAuthenticationException(
    val errorCode: ErrorCode,
) : RuntimeException(errorCode.message)
