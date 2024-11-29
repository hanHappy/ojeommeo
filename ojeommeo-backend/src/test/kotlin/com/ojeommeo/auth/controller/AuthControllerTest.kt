package com.ojeommeo.auth.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ojeommeo.domain.auth.dto.SignUpRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
) {

    private val objectMapper = ObjectMapper()

    @Test
    fun `회원 가입 시 HTTP 201(created) 반환`() {
        mockMvc.post("/api/auth/signup") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                SignUpRequest(
                    username = "한상민",
                    nickname = "한해피",
                    password = "password",
                )
            )
        } .andExpect { status { isCreated() } }
    }

}
