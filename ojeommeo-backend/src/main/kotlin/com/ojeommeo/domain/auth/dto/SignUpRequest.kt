package com.ojeommeo.domain.auth.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SignUpRequest(
    @field:NotBlank(message = "아이디는 필수입니다")
    @field:Size(min = 1, max = 20, message = "아이디는 4-20자 사이여야 합니다")
    val username: String,
    @field:NotBlank(message = "닉네임은 필수입니다")
    @field:Size(min = 1, max = 10, message = "닉네임은 1-10자 사이여야 합니다")
    val nickname: String,
    @field:NotBlank(message = "비밀번호는 필수입니다")
    val password: String,
)
