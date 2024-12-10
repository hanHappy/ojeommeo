package com.ojeommeo.user.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.ojeommeo.domain.user.dto.SignUpRequest
import com.ojeommeo.domain.user.entity.User
import com.ojeommeo.domain.user.mapper.toUserEntity
import com.ojeommeo.domain.user.service.UserService
import com.ojeommeo.user.testSignInRequest
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
class UserControllerTest
    @Autowired
    constructor(
        private val mockMvc: MockMvc,
        @MockkBean private val userService: UserService,
        @MockkBean private val passwordEncoder: PasswordEncoder,
    ) {
        private val objectMapper = ObjectMapper()

        @BeforeEach
        fun beforeEach() {
            every {
                userService.signUp(any())
            } answers {
                firstArg()
            }
        }

        @Test
        fun `회원 가입 시 HTTP 201(created)를 반환한다`() {
            mockMvc.post("/api/users") {
                contentType = MediaType.APPLICATION_JSON
                accept = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(testSignInRequest())
            }.andExpect { status { isCreated() } }
        }

        @Test
        fun `username 파라미터가 없으면 Validation Exception이 발생하고 ErrorCode REQ_001를 반환한다`() {
            mockMvc.post("/api/users") {
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
        fun `UserController에서 DTO가 Entity로 변환되어 UserService에 UserEntity가 전달된다`() {
            every {
                passwordEncoder.encode(any())
            } returns "encodedPassword"

            mockMvc.post("/api/users") {
                contentType = MediaType.APPLICATION_JSON
                accept = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(testSignInRequest())
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

            val savedUser = userService.signUp(testSignInRequest().toUserEntity(passwordEncoder))

            assertEquals(expected.username, savedUser.username)
        }
    }
