package com.yeseul.bookmark.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import com.fasterxml.jackson.databind.ObjectMapper
import com.yeseul.bookmark.controller.dto.request.RequestMemberDto
import com.yeseul.bookmark.domain.Member
import com.yeseul.bookmark.repository.MemberRepository
import org.junit.jupiter.api.BeforeAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.web.servlet.*

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // BeforeAll
class MemberControllerTest {

    @Autowired lateinit var mockMvc: MockMvc
    @Autowired lateinit var memberRepository: MemberRepository
    @Autowired lateinit var objectMapper: ObjectMapper
    @Autowired lateinit var passwordEncoder: PasswordEncoder
    lateinit var testMember: Member

    @BeforeAll
    fun beforeAll() {
        testMember = Member("test101", passwordEncoder.encode("test101"))
        memberRepository.save(testMember)
    }

    @Test
    @DisplayName("회원가입 테스트")
    fun `회원가입`() {

        val memberDto = RequestMemberDto("saveUsername", "savePassword")
        val memberDtoJson = objectMapper.writeValueAsString(memberDto)

        mockMvc.post("/v1/members/signup")
        {
            contentType = MediaType.APPLICATION_JSON
            content = memberDtoJson
        }.andExpect {
            status { isCreated() }
        }.andDo { print() }
    }

    @Test
    @DisplayName("로그인 테스트")
    fun `로그인 테스트`() {

        val loginDto = RequestMemberDto("test101", "test101")
        val loginDtoJson = objectMapper.writeValueAsString(loginDto)

        mockMvc.post("/v1/members/login")
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