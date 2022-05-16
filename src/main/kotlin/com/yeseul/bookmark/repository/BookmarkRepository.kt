package com.yeseul.bookmark.repository

import com.yeseul.bookmark.domain.Bookmark
import com.yeseul.bookmark.domain.Folder
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkRepository: JpaRepository<Bookmark, Long> {

    fun findAllByFolder(folder: Folder, pageable: Pageable): Page<Bookmark>
}