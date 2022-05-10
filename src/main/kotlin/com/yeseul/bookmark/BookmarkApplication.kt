package com.yeseul.bookmark

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class BookmarkApplication

fun main(args: Array<String>) {
    runApplication<BookmarkApplication>(*args)
}
