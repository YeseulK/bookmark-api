package com.yeseul.bookmark

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookmarkApplication

fun main(args: Array<String>) {
    runApplication<BookmarkApplication>(*args)
}
