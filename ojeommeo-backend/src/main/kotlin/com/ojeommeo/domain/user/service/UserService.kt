package com.ojeommeo.domain.user.service

import com.ojeommeo.domain.user.dto.LoginRequest
import com.ojeommeo.domain.user.entity.User
import com.ojeommeo.domain.user.repository.UserRepository
import com.ojeommeo.exception.ErrorCode
import com.ojeommeo.exception.ServiceException
import com.ojeommeo.security.entity.JwtRefreshToken
import com.ojeommeo.security.repository.JwtRefreshTokenRepository
import com.ojeommeo.security.service.JwtService
import jakarta.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager,
    private val jwtRefreshTokenRepository: JwtRefreshTokenRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun getUserById(id: Long): User? =
        userRepository.findById(id)
            .orElseThrow { ServiceException(ErrorCode.USER_NOT_FOUND) }

    fun getUserByUsername(username: String): User? =
        userRepository.findByUsername(username)
            .orElseThrow { ServiceException(ErrorCode.USER_NOT_FOUND) }

    fun signUp(user: User): User {
        if (userRepository.existsByUsername(user.username)) {
            throw ServiceException(ErrorCode.DUPLICATE_USERNAME)
        }
        if (userRepository.existsByNickname(user.nickname)) {
            throw ServiceException(ErrorCode.DUPLICATE_NICKNAME)
        }

        return userRepository.save(user)
    }

    @Transactional
    fun login(loginRequest: LoginRequest): String {
        val authentication =
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password),
            )
        SecurityContextHolder.getContext().authentication = authentication

        val userDetails = authentication.principal as UserDetails
        val accessToken = jwtService.generateAccessToken(userDetails)
        val refreshToken = jwtService.generateRefreshToken(userDetails)

        val user = getUserByUsername(loginRequest.username)!!

        jwtRefreshTokenRepository.save(
            JwtRefreshToken(
                user = user,
                token = refreshToken,
                expireAt = jwtService.extractExpiration(refreshToken),
            ),
        )

        return accessToken
    }
}
