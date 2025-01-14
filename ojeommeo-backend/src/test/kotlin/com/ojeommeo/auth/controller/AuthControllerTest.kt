package com.ojeommeo.auth.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.ojeommeo.auth.testLoginRequest
import com.ojeommeo.auth.testLoginResponse
import com.ojeommeo.auth.testSignUpRequest
import com.ojeommeo.domain.auth.dto.LoginResponse
import com.ojeommeo.domain.auth.dto.SignUpRequest
import com.ojeommeo.domain.auth.mapper.toUserEntity
import com.ojeommeo.domain.auth.service.AuthService
import com.ojeommeo.domain.user.entity.User
import io.mockk.every
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest
    @Autowired
    constructor(
        private val mockMvc: MockMvc,
        @MockkBean private val authService: AuthService,
        @MockkBean private val passwordEncoder: PasswordEncoder,
    ) {
        private val objectMapper = ObjectMapper()

        @BeforeEach
        fun beforeEach() {
            every {
                passwordEncoder.encode(any())
            } returns "encodedPassword"

            every {
                authService.signUp(any())
            } answers {
                firstArg()
            }
        }

        @Test
        fun `회원 가입 시 201(created)를 반환한다`() {
            mockMvc.post("/api/auth/sign-up") {
                contentType = MediaType.APPLICATION_JSON
                accept = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(testSignUpRequest())
            }.andExpect {
                status { isCreated() }
            }.andDo {
                print()
            }
        }

        @Test
        fun `username 파라미터가 없으면 Validation Exception이 발생하고 ErrorCode REQ_001를 반환한다`() {
            mockMvc.post("/api/auth/sign-up") {
                contentType = MediaType.APPLICATION_JSON
                accept = MediaType.APPLICATION_JSON
                content =
                    objectMapper.writeValueAsString(
                        SignUpRequest(
                            username = "",
                            nickname = "한해피",
                            password = "password",
                        ),
                    )
            }.andExpect {
                status { isBadRequest() }
                jsonPath("$.code") { value("REQ_001") }
            }
        }

        @Test
        fun `회원 가입 시 UserController에서 DTO가 Entity로 변환되어 UserService에 UserEntity가 전달된다`() {
            mockMvc.post("/api/auth/sign-up") {
                contentType = MediaType.APPLICATION_JSON
                accept = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(testSignUpRequest())
            }

            val expected =
                User(
                    id = null,
                    username = "han",
                    nickname = "한해피",
                    password = "ojeommeo123!",
                    profileImage = null,
                    lastLoginAt = null,
                    createdAt = null,
                    updatedAt = null,
                )

            val savedUser = authService.signUp(testSignUpRequest().toUserEntity(passwordEncoder))

            assertEquals(expected.username, savedUser.username)
        }

        @Test
        fun `로그인 시 200을 반환한다`() {
            every {
                authService.login(any())
            } returns testLoginResponse()

            mockMvc.post("/api/auth/login") {
                contentType = MediaType.APPLICATION_JSON
                accept = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(testLoginRequest())
            }.andExpect {
                status { isOk() }
            }
        }
    }
