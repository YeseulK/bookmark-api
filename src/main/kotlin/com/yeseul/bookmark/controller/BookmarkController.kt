package com.yeseul.bookmark.controller

import com.yeseul.bookmark.controller.dto.BookmarkDto
import com.yeseul.bookmark.domain.Bookmark
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

    @GetMapping
    fun getBookmarks(): ResponseEntity<ApiResponse<List<Bookmark>>> {
        val result = bookmarkService.findBookmarks()
        val response = ApiResponse(result, ApiPageMeta(total = result.count()))
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getBookmark(@PathVariable id: Long): ResponseEntity<ApiResponse<Bookmark>> {
        return ResponseEntity.ok(ApiResponse(bookmarkService.findBookmark(id)))
    }

    @PostMapping
    fun postBookmark(@RequestBody body: BookmarkDto) {
        bookmarkService.createBookmark(body)
    }

    @PatchMapping("/{id}")
    fun updateBookmarkTitle(@PathVariable id: Long, @RequestBody body: BookmarkDto) {
        bookmarkService.updateBookmark(id, body)
    }

    @DeleteMapping("/{id}")
    fun deleteBookmark(@PathVariable id: Long) {
        bookmarkService.deleteBookmark(id)
    }
}


