package com.moviesearcher.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "출연진 정보 DTO")
data class CastDto(
    @Schema(description = "배우 이름")
    val name: String,
    @Schema(description = "캐릭터명")
    val character: String?,
    @Schema(description = "프로필 이미지 경로")
    val profilePath: String?
) 