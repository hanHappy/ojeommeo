package com.ojeommeo.security.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtService(
    @Value("\${jwt.secret-key}") private val SECRET_KEY: String,
    @Value("\${jwt.access-token-expiration}") private val ACCESS_TOKEN_EXPIRATION: String,
    @Value("\${jwt.refresh-token-expiration}") private val REFRESH_TOKEN_EXPIRATION: String,
) {
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(SECRET_KEY.toByteArray())

    fun generateAccessToken(userDetails: UserDetails): String =
        Jwts.builder()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION.toLong()))
            .signWith(secretKey)
            .compact()

    fun generateRefreshToken(userDetails: UserDetails): String =
        Jwts.builder()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION.toLong()))
            .signWith(secretKey)
            .compact()

    fun isTokenValid(
        token: String,
        userDetails: UserDetails,
    ): Boolean {
        val username = extractUsername(token)
        return (
            username != null &&
                username == userDetails.username &&
                !isTokenExpired(token)
        )
    }

    fun extractAllClaims(token: String): Claims =
        Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload

    fun <T> extractClaim(
        token: String,
        claimResolver: (Claims) -> T,
    ): T = claimResolver(extractAllClaims(token))

    fun extractUsername(token: String): String? = extractAllClaims(token).subject

    fun extractExpiration(token: String): Date = extractAllClaims(token).expiration

    fun isTokenExpired(token: String): Boolean = extractExpiration(token).before(Date())
}
