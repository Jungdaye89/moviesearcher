package com.moviesearcher.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "영화 상세 정보 DTO")
data class MovieDetailDto(
    @Schema(description = "영화 ID")
    val id: Long,
    @Schema(description = "영화 제목")
    val title: String,
    @Schema(description = "영화 줄거리")
    val overview: String?,
    @Schema(description = "포스터 이미지 경로")
    val posterPath: String?,
    @Schema(description = "배경 이미지 경로")
    val backdropPath: String?,
    @Schema(description = "개봉일")
    val releaseDate: String?,
    @Schema(description = "평균 평점")
    val voteAverage: Double?,
    @Schema(description = "평점 투표 수")
    val voteCount: Int?,
    @Schema(description = "상영 시간(분)")
    val runtime: Int?,
    @Schema(description = "장르 목록")
    val genres: List<String>,
    @Schema(description = "출연진 목록")
    val cast: List<CastDto>,
    @Schema(description = "제작진 목록")
    val crew: List<CrewDto>
) 