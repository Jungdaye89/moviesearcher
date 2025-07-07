package com.moviesearcher.dto.response

import java.time.LocalDateTime

data class SignupResponseDto(
    val id: Long,
    val name: String,
    val createdAt: LocalDateTime
) 