package com.ojeommeo.exception

import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult

data class ErrorResponse(
    val code: String,
    val status: HttpStatus,
    val message: String,
    val errors: List<ValidationError>? = emptyList(),
) {
    companion object {
        fun of(errorCode: ErrorCode) =
            ErrorResponse(
                code = errorCode.code,
                status = errorCode.status,
                message = errorCode.message,
            )

        fun of(
            errorCode: ErrorCode,
            bindingResult: BindingResult,
        ) = ErrorResponse(
            code = errorCode.code,
            status = errorCode.status,
            message = errorCode.message,
            errors = ValidationError.of(bindingResult),
        )
    }
}

data class ValidationError(
    val field: String,
    val value: String,
    val message: String,
) {
    companion object {
        fun of(bindingResult: BindingResult): List<ValidationError> =
            bindingResult.fieldErrors.map { error ->
                ValidationError(
                    field = error.field,
                    value = error.rejectedValue?.toString() ?: "",
                    message = error.defaultMessage ?: "",
                )
            }
    }
}
