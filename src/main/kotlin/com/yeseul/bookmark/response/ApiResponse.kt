package com.yeseul.bookmark.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApiResponse<T> @JvmOverloads constructor(
    var data: T? = null,
    var meta: MutableMap<String, Any>? = null,
    var errors: MutableList<ApiResponseError>? = null
) {
    constructor(data: T, page: ApiPageMeta): this(data, mutableMapOf("page" to page))
}

data class ApiPageMeta(
    val total: Int
)