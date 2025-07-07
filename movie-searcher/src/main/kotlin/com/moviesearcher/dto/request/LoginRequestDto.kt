package com.moviesearcher.dto.request

import jakarta.validation.constraints.NotBlank

data class LoginRequestDto(
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val password: String
) 