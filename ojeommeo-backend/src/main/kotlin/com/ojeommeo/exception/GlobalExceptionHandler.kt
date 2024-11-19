package com.ojeommeo.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private fun handleException(errorCode: ErrorCode) =
        ErrorResponse.of(errorCode).let { errorResponse ->
            ResponseEntity.status(errorResponse.status).body(errorResponse)
        }

    @ExceptionHandler(ServiceException::class)
    fun handleServiceException(e: ServiceException) =
        handleException(e.errorCode)

    @ExceptionHandler(UsernameNotFoundException::class)
    fun handleUsernameNotFoundException(e: UsernameNotFoundException) =
        handleException(ErrorCode.USER_NOT_FOUND)

    @ExceptionHandler(Exception::class)
    fun handleGlobalException(e: Exception) =
        handleException(ErrorCode.INTERNAL_SERVER_ERROR)
}
