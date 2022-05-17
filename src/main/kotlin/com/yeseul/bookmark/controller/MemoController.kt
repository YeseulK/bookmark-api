package com.yeseul.bookmark.controller

import com.yeseul.bookmark.controller.dto.request.CreateMemoDto
import com.yeseul.bookmark.controller.dto.response.MemoDto
import com.yeseul.bookmark.response.ApiPageMeta
import com.yeseul.bookmark.response.ApiResponse
import com.yeseul.bookmark.service.MemoService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/memos")
class MemoController(
    private val memoService: MemoService
) {

    @GetMapping
    fun getMemos(
        @PageableDefault(page = 0, size = 20, sort = ["id"], direction = Sort.Direction.DESC) pageable: Pageable
    ): ResponseEntity<ApiResponse<List<MemoDto>>> {
        val result = memoService.findMemos(pageable)
        val response = ApiResponse(result.data, ApiPageMeta(pageable.pageNumber, pageable.pageSize, result.total))
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getMemo(@PathVariable id: Long): ResponseEntity<ApiResponse<MemoDto>> {
        return ResponseEntity.ok(ApiResponse(memoService.findMemo(id)))
    }

    @PostMapping
    fun postMemo(@RequestBody body: CreateMemoDto): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.CREATED).body(memoService.createMemo(body))
    }

    @DeleteMapping("/{id}")
    fun deleteMemo(@PathVariable id: Long): ResponseEntity<Any> {
        memoService.deleteMemo(id)
        return ResponseEntity.noContent().build()
    }
}