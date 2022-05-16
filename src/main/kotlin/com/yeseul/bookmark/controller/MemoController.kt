package com.yeseul.bookmark.controller

import com.yeseul.bookmark.controller.dto.request.CreateMemoDto
import com.yeseul.bookmark.controller.dto.response.MemoDto
import com.yeseul.bookmark.response.ApiPageMeta
import com.yeseul.bookmark.response.ApiResponse
import com.yeseul.bookmark.service.MemoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/memos")
class MemoController(
    private val memoService: MemoService
) {

    @GetMapping
    fun getMemos(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") limit: Int
    ): ResponseEntity<ApiResponse<List<MemoDto>>> {
        val result = memoService.findMemos(page, limit)
        val response = ApiResponse(result.data, ApiPageMeta(page, limit, result.total))
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getMemo(@PathVariable id: Long): ResponseEntity<ApiResponse<MemoDto>> {
        return ResponseEntity.ok(ApiResponse(memoService.findMemo(id)))
    }

    @PostMapping
    fun postMemo(@RequestBody body: CreateMemoDto) {
        memoService.createMemo(body)
    }

    @DeleteMapping("/{id}")
    fun deleteMemo(@PathVariable id: Long) {
        memoService.deleteMemo(id)
    }
}