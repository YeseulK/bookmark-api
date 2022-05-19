package com.yeseul.bookmark.controller

import com.yeseul.bookmark.controller.dto.request.CreateMemoDto
import com.yeseul.bookmark.controller.dto.request.EditMemoDto
import com.yeseul.bookmark.controller.dto.response.MemoDto
import com.yeseul.bookmark.response.ApiPageMeta
import com.yeseul.bookmark.response.ApiResponse
import com.yeseul.bookmark.service.MemoService
import io.swagger.annotations.ApiOperation
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

    @ApiOperation(value = "북마크 메모 리스트 조회")
    @GetMapping
    fun getMemos(
        @PageableDefault(page = 0, size = 20, sort = ["id"], direction = Sort.Direction.DESC) pageable: Pageable
    ): ResponseEntity<ApiResponse<List<MemoDto>>> {
        val result = memoService.findMemos(pageable)
        val response = ApiResponse(result.data, ApiPageMeta(pageable.pageNumber, pageable.pageSize, result.total))
        return ResponseEntity.ok(response)
    }

    @ApiOperation(value = "북마크 메모 조회")
    @GetMapping("/{id}")
    fun getMemo(@PathVariable id: Long): ResponseEntity<ApiResponse<MemoDto>> {
        return ResponseEntity.ok(ApiResponse(memoService.findMemo(id)))
    }

    @ApiOperation(value = "북마크 메모 생성")
    @PostMapping
    fun postMemo(@RequestBody body: CreateMemoDto): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.CREATED).body(memoService.createMemo(body))
    }

    @ApiOperation(value = "북마크 메모 수정")
    @PatchMapping("/{id}")
    fun patchMemo(
        @PathVariable id: Long,
        @RequestBody body: EditMemoDto
    ): ResponseEntity<ApiResponse<MemoDto>> {
        val result = memoService.editMemo(id, body)
        return ResponseEntity.ok(ApiResponse(result))
    }

    @ApiOperation(value = "북마크 메모 삭제")
    @DeleteMapping("/{id}")
    fun deleteMemo(@PathVariable id: Long): ResponseEntity<Any> {
        memoService.deleteMemo(id)
        return ResponseEntity.noContent().build()
    }
}