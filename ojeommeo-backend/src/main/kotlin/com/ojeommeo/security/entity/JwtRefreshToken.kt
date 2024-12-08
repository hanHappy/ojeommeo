package com.ojeommeo.security.entity

import com.ojeommeo.domain.user.entity.User
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.util.*

@Entity
@Table(name = "jwt_refresh_token")
class JwtRefreshToken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    var user: User,
    @Column(nullable = false)
    @Comment("refresh token")
    val token: String,
    @Column(nullable = false)
    @Comment("refresh token 만료일")
    val expireAt: Date,
)
