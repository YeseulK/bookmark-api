package com.yeseul.bookmark.controller

import com.yeseul.bookmark.controller.dto.MemberDto
import com.yeseul.bookmark.domain.Member
import com.yeseul.bookmark.response.ApiPageMeta
import com.yeseul.bookmark.response.ApiResponse
import com.yeseul.bookmark.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @GetMapping
    fun getMembers(): ResponseEntity<ApiResponse<List<Member>>> {
        val result = memberService.findMembers()
        val response = ApiResponse(result, ApiPageMeta(total = result.count()))
        return ResponseEntity.ok(response)
    }
    @GetMapping("/{id}")
    fun getMember(@PathVariable id: Long): ResponseEntity<ApiResponse<Member>> {
        return ResponseEntity.ok(ApiResponse(memberService.findMember(id)))
    }

    @PutMapping("/{id}")
    fun putMember(
        @PathVariable id: Long,
        @RequestBody body: MemberDto) {
        memberService.updateMember(id, body)
    }

    @DeleteMapping("/{id}")
    fun deleteMember(@PathVariable id: Long) {
        memberService.deleteMember(id)
    }
}