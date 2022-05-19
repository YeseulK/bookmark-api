package com.yeseul.bookmark.controller

import com.yeseul.bookmark.controller.dto.request.RequestMemberDto
import com.yeseul.bookmark.controller.dto.response.MemberDto
import com.yeseul.bookmark.response.ApiResponse
import com.yeseul.bookmark.service.MemberService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/members")
class MemberController(
    private val memberService: MemberService
) {

    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    fun signup(@RequestBody body: RequestMemberDto): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.signup(body))
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    fun login(@RequestBody body: RequestMemberDto): ResponseEntity<String> {
        return ResponseEntity.ok().body(memberService.login(body))
    }

    @ApiOperation(value = "사용자 리스트 조회")
    @GetMapping
    fun getMembers(): ResponseEntity<ApiResponse<List<MemberDto>>> {
        return ResponseEntity.ok(ApiResponse(memberService.findMembers()))
    }

    @ApiOperation(value = "사용자 조회")
    @GetMapping("/{id}")
    fun getMember(@PathVariable id: Long): ResponseEntity<ApiResponse<MemberDto>> {
        return ResponseEntity.ok(ApiResponse(memberService.findMember(id)))
    }

    @ApiOperation(value = "사용자 수정")
    @PutMapping("/{id}")
    fun putMember(
        @PathVariable id: Long,
        @RequestBody body: RequestMemberDto): ResponseEntity<Unit> {
        return ResponseEntity.ok().body(memberService.updateMember(id, body))
    }

    @ApiOperation(value = "사용자 삭제")
    @DeleteMapping("/{id}")
    fun deleteMember(@PathVariable id: Long): ResponseEntity<Any> {
        memberService.deleteMember(id)
        return ResponseEntity.noContent().build()
    }
}