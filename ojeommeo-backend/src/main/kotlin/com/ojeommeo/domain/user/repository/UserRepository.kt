package com.ojeommeo.domain.user.repository

import com.ojeommeo.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String?): Optional<User>

    fun existsByUsername(username: String?): Boolean

    fun existsByNickname(nickname: String?): Boolean
}
