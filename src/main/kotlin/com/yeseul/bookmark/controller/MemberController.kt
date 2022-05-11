package com.yeseul.bookmark.controller

import com.yeseul.bookmark.controller.dto.request.RequestMemberDto
import com.yeseul.bookmark.controller.dto.response.MemberDto
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
    fun signup(@RequestBody body: RequestMemberDto): ResponseEntity<Unit> {
        return ResponseEntity.status(201).body(memberService.signup(body))
    }

    @PostMapping("/login")
    fun login(@RequestBody body: RequestMemberDto): ResponseEntity<String> {
        return ResponseEntity.ok().body(memberService.login(body))
    }

    @GetMapping
    fun getMembers(): ResponseEntity<ApiResponse<List<MemberDto>>> {
        val result = memberService.findMembers()
        val response = ApiResponse(result, ApiPageMeta(total = result.count()))
        return ResponseEntity.ok(response)
    }
    @GetMapping("/{id}")
    fun getMember(@PathVariable id: Long): ResponseEntity<ApiResponse<MemberDto>> {
        return ResponseEntity.ok(ApiResponse(memberService.findMember(id)))
    }

    @PutMapping("/{id}")
    fun putMember(
        @PathVariable id: Long,
        @RequestBody body: RequestMemberDto) {
        memberService.updateMember(id, body)
    }

    @DeleteMapping("/{id}")
    fun deleteMember(@PathVariable id: Long) {
        memberService.deleteMember(id)
    }
}