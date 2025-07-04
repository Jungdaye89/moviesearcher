package com.moviesearcher.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "제작진 정보 DTO")
data class CrewDto(
    @Schema(description = "제작진 이름")
    val name: String,
    @Schema(description = "직책")
    val job: String?,
    @Schema(description = "프로필 이미지 경로")
    val profilePath: String?
) 