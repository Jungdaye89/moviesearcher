package com.moviesearcher.dto.response

import java.time.LocalDateTime

data class ApiResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val error: ErrorResponse? = null,
    val timestamp: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        fun <T> success(data: T): ApiResponse<T> {
            return ApiResponse(
                success = true,
                data = data
            )
        }
        
        fun <T> error(error: ErrorResponse): ApiResponse<T> {
            return ApiResponse(
                success = false,
                error = error
            )
        }
    }
} 