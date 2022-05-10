package com.yeseul.bookmark.service

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Value

@Service
class ExternalService {

    @Value("\${external.expire-time}")
    var expireTime: Long? = null

    @Value("\${external.secret-key}")
    var secretKey: String? = null
}

