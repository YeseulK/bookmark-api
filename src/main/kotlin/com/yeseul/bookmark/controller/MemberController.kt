package com.yeseul.bookmark.controller

import com.yeseul.bookmark.controller.dto.request.RequestMemberDto
import com.yeseul.bookmark.controller.dto.response.MemberDto
import com.yeseul.bookmark.response.ApiResponse
import com.yeseul.bookmark.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/members")
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping("/signup")
    fun signup(@RequestBody body: RequestMemberDto): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.signup(body))
    }

    @PostMapping("/login")
    fun login(@RequestBody body: RequestMemberDto): ResponseEntity<String> {
        return ResponseEntity.ok().body(memberService.login(body))
    }

    @GetMapping
    fun getMembers(): ResponseEntity<ApiResponse<List<MemberDto>>> {
        return ResponseEntity.ok(ApiResponse(memberService.findMembers()))
    }
    @GetMapping("/{id}")
    fun getMember(@PathVariable id: Long): ResponseEntity<ApiResponse<MemberDto>> {
        return ResponseEntity.ok(ApiResponse(memberService.findMember(id)))
    }

    @PutMapping("/{id}")
    fun putMember(
        @PathVariable id: Long,
        @RequestBody body: RequestMemberDto): ResponseEntity<Unit> {
        return ResponseEntity.ok().body(memberService.updateMember(id, body))
    }

    @DeleteMapping("/{id}")
    fun deleteMember(@PathVariable id: Long): ResponseEntity<Any> {
        memberService.deleteMember(id)
        return ResponseEntity.noContent().build()
    }
}