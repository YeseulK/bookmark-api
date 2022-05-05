package com.yeseul.bookmark.repository

import com.yeseul.bookmark.domain.Folder
import org.springframework.data.jpa.repository.JpaRepository

interface FolderRepository: JpaRepository<Folder, Long>