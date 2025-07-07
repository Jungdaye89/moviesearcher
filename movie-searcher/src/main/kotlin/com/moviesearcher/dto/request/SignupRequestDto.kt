package com.moviesearcher.dto.request

import jakarta.validation.constraints.NotBlank

data class SignupRequestDto(
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val password: String
) 