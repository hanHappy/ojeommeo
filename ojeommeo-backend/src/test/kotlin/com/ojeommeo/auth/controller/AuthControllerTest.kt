package com.ojeommeo.auth.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ojeommeo.auth.testSignInRequest
import com.ojeommeo.domain.user.dto.SignUpRequest
import com.ojeommeo.domain.user.entity.User
import com.ojeommeo.domain.user.service.UserService
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import com.ninjasquad.springmockk.MockkBean
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    @MockkBean private val userService: UserService
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
            content = objectMapper.writeValueAsString(testSignInRequest(999))
        } .andExpect { status { isCreated() } }
    }

    @Test
    fun `username 파라미터가 없으면 Validation Exception이 발생하고 error code REQ_001를 반환한다`() {
        mockMvc.post("/api/users") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                SignUpRequest(
                    id = 123,
                    username = "",
                    nickname = "한해피",
                    password = "password",
                )
            )
        } .andExpect {
            status { isBadRequest() }
            jsonPath("$.code") { value("REQ_001") }
        }
    }

    @Test
    fun `회원 가입 시 User 데이터가 저장된다`() {
        mockMvc.post("/api/users") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(testSignInRequest(999))
        }

        val expected = User (
            id = null,
            username = "han",
            nickname = "한해피",
            password = "ojeommeo123",
        )

        
    }

}
