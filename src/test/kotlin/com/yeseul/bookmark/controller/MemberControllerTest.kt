package com.yeseul.bookmark.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // BeforeAll
class MemberControllerTest {

    @Test
    @DisplayName("로그인 테스트")
    fun `jwt 토큰발급 테스트` () {
        // TODO:
    }
}