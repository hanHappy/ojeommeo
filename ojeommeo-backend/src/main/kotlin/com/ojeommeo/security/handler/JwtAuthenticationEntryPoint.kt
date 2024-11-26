package com.ojeommeo.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.ojeommeo.exception.ErrorCode
import com.ojeommeo.exception.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationEntryPoint (
    private val objectMapper: ObjectMapper
): AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        val errorCode = ErrorCode.UNAUTHORIZED

        response?.apply {
            contentType = MediaType.APPLICATION_JSON_VALUE
            status = errorCode.status.value()
        }

        objectMapper.writeValue(response?.outputStream, ErrorResponse.of(errorCode))
    }

}
