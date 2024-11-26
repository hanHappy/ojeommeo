package com.ojeommeo.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.ojeommeo.exception.ErrorCode
import com.ojeommeo.exception.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class JwtAccessDeniedHandler (
    private val objectMapper: ObjectMapper
): AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?
    ) {
        val errorCode = ErrorCode.FORBIDDEN

        response?.apply {
            contentType = MediaType.APPLICATION_JSON_VALUE
            status = errorCode.status.value()
        }

        objectMapper.writeValue(response?.outputStream, ErrorResponse.of(errorCode))
    }
}
