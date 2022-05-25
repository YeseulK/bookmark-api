package com.yeseul.bookmark.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import com.fasterxml.jackson.databind.ObjectMapper
import com.yeseul.bookmark.controller.dto.request.CreateUserDto
import com.yeseul.bookmark.domain.User
import com.yeseul.bookmark.repository.UserRepository
import org.junit.jupiter.api.BeforeAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.web.servlet.*

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Before All
class UserControllerTest {

    @Autowired lateinit var mockMvc: MockMvc
    @Autowired lateinit var userRepository: UserRepository
    @Autowired lateinit var objectMapper: ObjectMapper
    @Autowired lateinit var passwordEncoder: PasswordEncoder
    lateinit var testUser: User

    @BeforeAll
    fun beforeAll() {
        testUser = User("test101", passwordEncoder.encode("test101"))
        userRepository.save(testUser)
    }

    @Test
    @DisplayName("회원가입 테스트")
    fun `회원가입`() {

        val userDto = CreateUserDto("saveUsername", "savePassword")
        val userDtoJson = objectMapper.writeValueAsString(userDto)

        mockMvc.post("/v1/users/signup")
        {
            contentType = MediaType.APPLICATION_JSON
            content = userDtoJson
        }.andExpect {
            status { isCreated() }
        }.andDo { print() }
    }

    @Test
    @DisplayName("로그인 테스트")
    fun `로그인 테스트`() {

        val loginDto = CreateUserDto("test101", "test101")
        val loginDtoJson = objectMapper.writeValueAsString(loginDto)

        mockMvc.post("/v1/users/login")
        {
            contentType = MediaType.APPLICATION_JSON
            content = loginDtoJson
        }
            .andExpect {
                status { isOk() }
            }
            .andDo {
                print()
            }
    }
}