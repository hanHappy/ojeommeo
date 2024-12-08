package com.ojeommeo.user.service

import com.ojeommeo.domain.user.mapper.toUserEntity
import com.ojeommeo.domain.user.service.UserService
import com.ojeommeo.security.repository.JwtRefreshTokenRepository
import com.ojeommeo.user.testLoginRequest
import com.ojeommeo.user.testSignInRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootTest
class UserServiceTest
    @Autowired
    constructor(
        private val userService: UserService,
        private val passwordEncoder: PasswordEncoder,
        private val jwtRefreshTokenRepository: JwtRefreshTokenRepository,
    ) {
        @Test
        fun `회원 가입 시 DB에 데이터가 저장된다`() {
            val savedUser = userService.signUp(testSignInRequest(null).toUserEntity(passwordEncoder))
            assertThat(savedUser).isNotNull()

            val savedUserId = savedUser.id!!

            val retrivedUser = userService.getUserById(savedUserId)

            assertThat(savedUser.username).isEqualTo(retrivedUser!!.username)
        }

        @Test
        fun `로그인 시 access token을 반환한다`() {
            val savedUser = userService.signUp(testSignInRequest(null).toUserEntity(passwordEncoder))

            val user = userService.getUserByUsername(savedUser.username)
            assertThat(user).isNotNull()

            val accessToken = userService.login(testLoginRequest())
            assertThat(accessToken).isNotNull()
        }

        @Test
        fun `로그인 시 refresh token이 DB에 저장된다`() {
            // given
            val savedUser = userService.signUp(testSignInRequest(null).toUserEntity(passwordEncoder))
            assertThat(savedUser.id).isNotNull()

            // when
            userService.login(testLoginRequest())

            // then
            val refreshToken = jwtRefreshTokenRepository.findByUserId(userId = savedUser.id!!)
            assertThat(refreshToken?.token).isNotNull()
        }
        // i don't want this. go away. bye. play with me. bye.'
    }
