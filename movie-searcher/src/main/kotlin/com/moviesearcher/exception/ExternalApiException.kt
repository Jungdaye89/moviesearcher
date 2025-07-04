package com.moviesearcher.exception

class ExternalApiException(
    val errorCode: ErrorCode,
    override val message: String? = errorCode.message,
    override val cause: Throwable? = null
) : RuntimeException(message, cause) 