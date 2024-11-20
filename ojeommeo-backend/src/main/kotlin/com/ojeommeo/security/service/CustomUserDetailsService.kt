package com.ojeommeo.security.service

import com.ojeommeo.domain.user.repository.UserRepository
import com.ojeommeo.exception.ErrorCode
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class CustomUserDetailsService(
    private val userRepository: UserRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user =
            userRepository.findByUsername(username)
                .orElseThrow { UsernameNotFoundException(ErrorCode.USER_NOT_FOUND.message) }
        return CustomUserDetails(
            username = user.username,
            password = user.password,
            role = user.role,
            status = user.status,
        )
    }
}
