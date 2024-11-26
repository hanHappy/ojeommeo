package com.ojeommeo.security.filter

import com.ojeommeo.exception.ErrorCode
import com.ojeommeo.exception.JwtAuthenticationException
import com.ojeommeo.security.service.JwtService
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService,
) : OncePerRequestFilter() {
    /**
     * JWT 토큰 기반 인증 필터.
     * Authorization 헤더에서 JWT 토큰을 추출, 검증하여 인증을 처리함.
     *
     * 동작 과정:
     * 1. Authorization 헤더에서 Bearer 토큰 추출
     * 2. JWT 토큰 파싱 및 검증
     * 3. 토큰에서 사용자 정보 추출
     * 4. Security Context에 인증 정보 설정
     *
     * @param request HTTP 요청
     * @param response HTTP 응답
     * @param filterChain 필터 체인
     */
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val authHeader = request.getHeader("Authorization")

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        try {
            val jwt = authHeader.substring(7)
            val username = jwtService.extractUsername(jwt)

            if (username != null && SecurityContextHolder.getContext().authentication == null) {
                val userDetails = userDetailsService.loadUserByUsername(username)
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    val authToken =
                        UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.authorities,
                        )

                    authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authToken
                }
            }
        } catch (e: ExpiredJwtException) {
            throw JwtAuthenticationException(ErrorCode.TOKEN_EXPIRED)
        } catch (e: JwtException) {
            throw JwtAuthenticationException(ErrorCode.INVALID_TOKEN)
        } catch (e: UsernameNotFoundException) {
            throw JwtAuthenticationException(ErrorCode.USER_NOT_FOUND)
        } catch (e: Exception) {
            throw JwtAuthenticationException(ErrorCode.UNAUTHORIZED)
        }
        filterChain.doFilter(request, response)
    }
}
