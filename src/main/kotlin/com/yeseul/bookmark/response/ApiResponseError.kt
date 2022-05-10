package com.yeseul.bookmark.response

data class ApiResponseError(
    var status: String,
    var title: String,
    var values: Map<String, Any>? = null,
    var message: Any? = null
)