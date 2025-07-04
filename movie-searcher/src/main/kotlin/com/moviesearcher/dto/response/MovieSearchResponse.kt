package com.moviesearcher.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "영화 검색 결과 응답 DTO")
data class MovieSearchResponse(
    @Schema(description = "검색된 영화 목록")
    val movies: List<MovieDto>,
    @Schema(description = "페이지네이션 정보")
    val pagination: PaginationDto
) 