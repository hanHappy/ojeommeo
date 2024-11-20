package com.ojeommeo.security.service

import com.ojeommeo.domain.user.enums.UserRole
import com.ojeommeo.domain.user.enums.UserStatus
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    private val username: String,
    private val password: String,
    private val role: UserRole,
    private val status: UserStatus,
) : UserDetails {
    override fun getAuthorities() = listOf(SimpleGrantedAuthority("ROLE_${role.name}"))

    override fun getPassword() = password

    override fun getUsername() = username

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = status != UserStatus.LOCKED

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = status == UserStatus.ACTIVE
}
