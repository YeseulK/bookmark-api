package com.yeseul.bookmark.service

import com.yeseul.bookmark.controller.dto.BookmarkDto
import com.yeseul.bookmark.domain.Bookmark
import com.yeseul.bookmark.repository.BookmarkRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class BookmarkService(
    val bookmarkRepository: BookmarkRepository,
    val mapper: ModelMapper,
) {

    fun findBookmarks(): List<Bookmark> {
        return bookmarkRepository.findAll().toList()
    }

    fun findBookmark(id: Long): Bookmark {
        return bookmarkRepository.findById(id).orElse(null)
    }

    fun createBookmark(dto: BookmarkDto): Bookmark {
        val entity = mapper.map(dto, Bookmark::class.java)
        return bookmarkRepository.save(entity)
    }

    fun updateBookmark(id: Long, dto: BookmarkDto) {
        val bookmark = bookmarkRepository.findById(id)

    }

    fun deleteBookmark(id: Long) {
        bookmarkRepository.deleteById(id)
    }

}