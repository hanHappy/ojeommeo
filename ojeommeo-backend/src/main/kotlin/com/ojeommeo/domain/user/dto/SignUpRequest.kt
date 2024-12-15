package com.ojeommeo.domain.user.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class SignUpRequest(
    @field:NotBlank(message = "아이디를 입력해 주세요")
    @field:Size(min = 1, max = 12, message = "1-12자 사이의 아이디를 입력해 주세요")
    @field:Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문, 숫자만 입력할 수 있습니다")
    val username: String,
    @field:NotBlank(message = "닉네임을 입력해 주세요")
    @field:Size(min = 1, max = 10, message = "1-10자 사이의 닉네임을 입력해 주세요")
    val nickname: String,
    @field:NotBlank(message = "비밀번호를 입력해 주세요")
    @field:Size(min = 1, max = 18, message = "1-18자 사이의 비밀번호를 입력해 주세요")
    val password: String,
)
