package com.ojeommeo.domain.user.service

import com.ojeommeo.domain.user.entity.User
import com.ojeommeo.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userRepository: UserRepository,
) {
    fun signUp(user: User) = userRepository.save(user)
}
