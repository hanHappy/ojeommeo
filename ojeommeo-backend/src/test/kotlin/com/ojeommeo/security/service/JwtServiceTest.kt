package com.ojeommeo.security.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JwtServiceTest {
    @Value("\${jwt.secret-key}")
    private lateinit var SECRET_KEY: String

    @Test
    fun `jwt secret-token 가져오기 테스트`() {
        assertThat(SECRET_KEY).isNotEmpty()
        assertThat(SECRET_KEY).isEqualTo("4aVunQqIBR83g9CIu/CY9j2Mtyd1n+LA+BPIH1X0/NK24aIODRjkjOUw5iBgBTfL")
    }
}
