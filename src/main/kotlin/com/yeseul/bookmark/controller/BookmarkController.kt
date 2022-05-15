package com.yeseul.bookmark.controller

import com.yeseul.bookmark.controller.dto.request.CreateBookmarkDto
import com.yeseul.bookmark.controller.dto.response.BookmarkDto
import com.yeseul.bookmark.response.ApiPageMeta
import com.yeseul.bookmark.response.ApiResponse
import com.yeseul.bookmark.service.BookmarkService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/v1/folders/{folderId}/bookmarks")
class BookmarkController(
    private val bookmarkService: BookmarkService
) {

    @GetMapping
    fun getBookmarks(
        @PathVariable folderId: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") limit: Int
    ): ResponseEntity<ApiResponse<List<BookmarkDto>>> {
        val result = bookmarkService.findBookmarks(page, limit)
        val response = ApiResponse(result.data, ApiPageMeta(page, limit, result.total))
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getBookmark(
        @PathVariable folderId: String,
        @PathVariable id: Long
    ): ResponseEntity<ApiResponse<BookmarkDto>> {
        return ResponseEntity.ok(ApiResponse(bookmarkService.findBookmark(id)))
    }

    @PostMapping
    fun postBookmark(
        request: HttpServletRequest,
        @PathVariable folderId: String,
        @RequestBody body: CreateBookmarkDto) {
        val username = request.getAttribute("username") as String
        bookmarkService.createBookmark(username, folderId.toLong(), body)
    }

    @DeleteMapping("/{id}")
    fun deleteBookmark(
        @PathVariable folderId: String,
        @PathVariable id: Long) {
        bookmarkService.deleteBookmark(id)
    }
}


