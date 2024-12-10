package com.ojeommeo.security.config

import com.ojeommeo.security.filter.JwtAuthenticationFilter
import com.ojeommeo.security.handler.JwtAccessDeniedHandler
import com.ojeommeo.security.handler.JwtAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val jwtAccessDeniedHandler: JwtAccessDeniedHandler,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val authenticationConfiguration: AuthenticationConfiguration,
) {
    // https://docs.spring.io/spring-security/reference/servlet/configuration/java.html#jc-httpsecurity
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .csrf { it.disable() }
            .cors { corsConfigurationSource() }
            .authorizeHttpRequests { auth ->
                auth.requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/users/login").permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling {
                it.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .accessDeniedHandler(jwtAccessDeniedHandler)
            }
            .authenticationManager(authenticationManager(authenticationConfiguration))
            .build()

    /**
     * CORS(Cross-Origin Resource Sharing) 설정을 위한 Configuration Source 생성
     *
     * - 허용 origin : http://localhost:5173
     * - 허용 HTTP 메서드 : GET, POST, PUT, PATCH, DELETE
     * - 허용 헤더 : Authorization, Content-Type
     * - Credentials 허용 : true
     * - 프리플라이트 캐시 시간 : 3600초(1시간)
     *
     * @return [CorsConfigurationSource] CORS 설정이 적용된 Configuration Source
     */
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration =
            CorsConfiguration().apply {
                allowedOrigins = listOf("http://localhost:5173")
                allowedMethods = listOf("GET", "POST", "PUT", "PATCH", "DELETE")
                allowedHeaders = listOf("Authorization", "Content-Type")
                allowCredentials = true
                maxAge = 3600L
            }

        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", configuration)
        }
    }

    /**
     * 사용자 인증을 처리하는 AuthenticationManager Bean 생성
     *
     * AuthenticationManager는 다음과 같은 인증 처리를 담당함:
     * - username/password 기반 로그인 인증
     * - JWT 토큰 기반 인증
     * - OAuth 등 소셜 로그인 인증
     *
     * example:
     * ```
     * val authentication = authenticationManager.authenticate(
     *     UsernamePasswordAuthenticationToken(username, password)
     * )
     * ```
     *
     * @param authenticationConfiguration Spring Security의 인증 설정 정보
     * @return [AuthenticationManager] 인증 처리를 담당하는 매니저 반환
     */
    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager =
        authenticationConfiguration.authenticationManager

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
