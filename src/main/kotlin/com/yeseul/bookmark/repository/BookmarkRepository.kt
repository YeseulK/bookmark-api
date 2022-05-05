package com.yeseul.bookmark.repository

import com.yeseul.bookmark.domain.Bookmark
import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkRepository: JpaRepository<Bookmark, Long>