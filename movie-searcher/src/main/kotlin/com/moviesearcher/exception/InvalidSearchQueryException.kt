package com.moviesearcher.exception

class InvalidSearchQueryException(
    query: String,
    message: String = ErrorCode.INVALID_SEARCH_QUERY.message
) : RuntimeException(message) {
    val errorCode = ErrorCode.INVALID_SEARCH_QUERY
    val query = query
} 