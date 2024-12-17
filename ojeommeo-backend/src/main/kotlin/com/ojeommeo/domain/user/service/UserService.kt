package com.ojeommeo.domain.user.service

import com.ojeommeo.domain.user.entity.User
import com.ojeommeo.domain.user.repository.UserRepository
import com.ojeommeo.exception.ErrorCode
import com.ojeommeo.exception.ServiceException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun getUserById(id: Long): User? =
        userRepository.findById(id)
            .orElseThrow { ServiceException(ErrorCode.USER_NOT_FOUND) }

    fun getUserByUsername(username: String): User? =
        userRepository.findByUsername(username)
            .orElseThrow { ServiceException(ErrorCode.USER_NOT_FOUND) }
}
