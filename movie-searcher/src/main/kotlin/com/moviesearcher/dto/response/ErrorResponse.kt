package com.moviesearcher.dto.response

data class ErrorResponse(
    val code: String,
    val message: String,
    val details: String? = null
) 