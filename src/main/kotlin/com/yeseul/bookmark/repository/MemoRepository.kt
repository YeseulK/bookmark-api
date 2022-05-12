package com.yeseul.bookmark.repository

import com.yeseul.bookmark.domain.Memo
import org.springframework.data.jpa.repository.JpaRepository

interface MemoRepository: JpaRepository<Memo, Long>