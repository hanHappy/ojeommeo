package com.ojeommeo.domain.user.repository

import com.ojeommeo.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, String> {
    fun findByUsername(username: String?): Optional<User>
}
