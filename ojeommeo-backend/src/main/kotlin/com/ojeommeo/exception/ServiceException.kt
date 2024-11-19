package com.ojeommeo.exception

class ServiceException(
    val errorCode: ErrorCode,
) : RuntimeException(errorCode.message)
