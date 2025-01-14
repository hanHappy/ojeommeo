package com.ojeommeo.auth.service

import com.ojeommeo.auth.testLoginRequest
import com.ojeommeo.auth.testSignUpRequest
import com.ojeommeo.domain.auth.mapper.toUserEntity
import com.ojeommeo.domain.auth.service.AuthService
import com.ojeommeo.domain.user.service.UserService
import com.ojeommeo.security.repository.JwtRefreshTokenRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootTest
class AuthServiceTest
    @Autowired
    constructor(
        private val authService: AuthService,
        private val userService: UserService,
        private val passwordEncoder: PasswordEncoder,
        private val jwtRefreshTokenRepository: JwtRefreshTokenRepository,
    ) {
        @Test
        fun `회원 가입 시 DB에 데이터가 저장된다`() {
            val savedUser = authService.signUp(testSignUpRequest().toUserEntity(passwordEncoder))
            assertThat(savedUser).isNotNull()

            val savedUserId = savedUser.id!!

            val retrivedUser = userService.getUserById(savedUserId)

            assertThat(savedUser.username).isEqualTo(retrivedUser!!.username)
        }

        @Test
        fun `로그인 시 access token을 반환한다`() {
            val savedUser = authService.signUp(testSignUpRequest().toUserEntity(passwordEncoder))

            val user = userService.getUserByUsername(savedUser.username)
            assertThat(user).isNotNull()

            val accessToken = authService.login(testLoginRequest())
            assertThat(accessToken).isNotNull()
        }

        @Test
        fun `로그인 시 refresh token이 DB에 저장된다`() {
            // given
            val savedUser = authService.signUp(testSignUpRequest().toUserEntity(passwordEncoder))
            assertThat(savedUser.id).isNotNull()

            // when
            authService.login(testLoginRequest())

            // then
            val refreshToken = jwtRefreshTokenRepository.findByUserId(userId = savedUser.id!!)
            assertThat(refreshToken?.token).isNotNull()
        }
    }
