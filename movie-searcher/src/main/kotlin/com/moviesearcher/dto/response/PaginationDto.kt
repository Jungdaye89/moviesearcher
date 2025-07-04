package com.moviesearcher.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "페이지네이션 정보 DTO")
data class PaginationDto(
    @Schema(description = "현재 페이지 번호")
    val page: Int,
    @Schema(description = "전체 페이지 수")
    val totalPages: Int,
    @Schema(description = "전체 결과 수")
    val totalResults: Int,
    @Schema(description = "다음 페이지 존재 여부")
    val hasNext: Boolean,
    @Schema(description = "이전 페이지 존재 여부")
    val hasPrevious: Boolean
) 