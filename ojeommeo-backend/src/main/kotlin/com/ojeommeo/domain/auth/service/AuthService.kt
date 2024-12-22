package com.ojeommeo.domain.auth.service

import com.ojeommeo.domain.auth.dto.LoginRequest
import com.ojeommeo.domain.auth.dto.LoginResponse
import com.ojeommeo.domain.user.entity.User
import com.ojeommeo.domain.user.mapper.toLoginResponse
import com.ojeommeo.domain.user.repository.UserRepository
import com.ojeommeo.exception.ErrorCode
import com.ojeommeo.exception.ServiceException
import com.ojeommeo.security.entity.JwtRefreshToken
import com.ojeommeo.security.repository.JwtRefreshTokenRepository
import com.ojeommeo.security.service.JwtService
import jakarta.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager,
    private val jwtRefreshTokenRepository: JwtRefreshTokenRepository,
) {
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
    fun login(loginRequest: LoginRequest): LoginResponse {
        try {
            val authentication =
                authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password),
                )
            SecurityContextHolder.getContext().authentication = authentication

            val userDetails = authentication.principal as UserDetails
            val accessToken = jwtService.generateAccessToken(userDetails)
            val refreshToken = jwtService.generateRefreshToken(userDetails)

            val user =
                userRepository.findByUsername(loginRequest.username)
                    .orElseThrow { ServiceException(ErrorCode.USER_NOT_FOUND) }

            jwtRefreshTokenRepository.save(
                JwtRefreshToken(
                    user = user,
                    token = refreshToken,
                    expireAt = jwtService.extractExpiration(refreshToken),
                ),
            )
            return user.toLoginResponse(accessToken)
        } catch (e: BadCredentialsException) {
            throw ServiceException(ErrorCode.INVALID_CREDENTIALS)
        } catch (e: AuthenticationException) {
            throw ServiceException(ErrorCode.UNAUTHORIZED)
        }
    }
}
