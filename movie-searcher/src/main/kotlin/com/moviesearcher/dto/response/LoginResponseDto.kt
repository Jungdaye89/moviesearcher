package com.moviesearcher.dto.response

data class LoginResponseDto(
    val token: String,
    val userId: Long,
    val name: String
) 