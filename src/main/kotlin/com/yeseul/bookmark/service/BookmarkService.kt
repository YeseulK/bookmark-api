package com.yeseul.bookmark.service

import com.yeseul.bookmark.controller.dto.request.CreateBookmarkDto
import com.yeseul.bookmark.controller.dto.response.BookmarkDto
import com.yeseul.bookmark.domain.Bookmark
import com.yeseul.bookmark.domain.Folder
import com.yeseul.bookmark.repository.BookmarkRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class BookmarkService(
    val bookmarkRepository: BookmarkRepository,
    val mapper: ModelMapper
) {

    fun findBookmarks(): List<BookmarkDto> {
        val entities = bookmarkRepository.findAll().toList()
        return entities.map { mapper.map(it, BookmarkDto::class.java) }
    }

    fun findBookmark(id: Long): BookmarkDto {
        val entity = bookmarkRepository.findById(id).orElse(null)
        return mapper.map(entity, BookmarkDto::class.java)
    }

    fun createBookmark(dto: CreateBookmarkDto) {
//        val entity = mapper.map(dto, Bookmark::class.java)
        val entity = Bookmark(
            url = dto.url,
            folder = Folder(id = dto.folderId)
        )
        bookmarkRepository.save(entity)
    }

    fun deleteBookmark(id: Long) {
        bookmarkRepository.deleteById(id)
    }
}