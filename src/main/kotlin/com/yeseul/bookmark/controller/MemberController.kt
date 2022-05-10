package com.yeseul.bookmark.controller

import com.yeseul.bookmark.controller.dto.MemberDto
import com.yeseul.bookmark.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/members")
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping("/signup")
    fun signup(@RequestBody memberDto: MemberDto): ResponseEntity<Unit> {
        return ResponseEntity.status(201).body(memberService.signup(memberDto))
    }

    @PostMapping("/login")
    fun login(@RequestBody memberDto: MemberDto): ResponseEntity<String> {
        return ResponseEntity.ok().body(memberService.login(memberDto))
    }
}