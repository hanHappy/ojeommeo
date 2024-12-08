package com.ojeommeo.domain.user.entity

import com.ojeommeo.domain.user.enums.UserRole
import com.ojeommeo.domain.user.enums.UserStatus
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(nullable = false, unique = true, length = 50)
    @Comment("로그인 아이디")
    val username: String,
    @Column(nullable = false, length = 128)
    @Comment("로그인 비밀번호")
    val password: String,
    @Column(nullable = false, length = 50)
    @Comment("닉네임")
    val nickname: String,
    @Column(name = "profile_image", length = 255)
    @Comment("프로필 이미지")
    val profileImage: String?,
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    @Comment("사용자 권한")
    val role: UserRole = UserRole.USER,
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    @Comment("계정 상태")
    val status: UserStatus = UserStatus.ACTIVE,
    @Column(name = "last_login_at")
    @Comment("마지막 로그인 일시")
    val lastLoginAt: LocalDateTime?,
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    @Comment("가입 일시")
    val createdAt: LocalDateTime?,
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    @Comment("업데이트 일시")
    val updatedAt: LocalDateTime?,
)
