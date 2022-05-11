package com.yeseul.bookmark.controller

import com.yeseul.bookmark.controller.dto.request.CreateBookmarkDto
import com.yeseul.bookmark.controller.dto.response.BookmarkDto
import com.yeseul.bookmark.response.ApiPageMeta
import com.yeseul.bookmark.response.ApiResponse
import com.yeseul.bookmark.service.BookmarkService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/bookmarks")
class BookmarkController(
    private val bookmarkService: BookmarkService
) {

    // TODO: paging 처리
    @GetMapping
    fun getBookmarks(): ResponseEntity<ApiResponse<List<BookmarkDto>>> {
        val result = bookmarkService.findBookmarks()
        val response = ApiResponse(result, ApiPageMeta(total = result.count()))
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getBookmark(@PathVariable id: Long): ResponseEntity<ApiResponse<BookmarkDto>> {
        val result = bookmarkService.findBookmark(id)
        return ResponseEntity.ok(ApiResponse(result))
    }

    @PostMapping
    fun postBookmark(@RequestBody body: CreateBookmarkDto) {
        bookmarkService.createBookmark(body)
    }

    @DeleteMapping("/{id}")
    fun deleteBookmark(@PathVariable id: Long) {
        bookmarkService.deleteBookmark(id)
    }
}


