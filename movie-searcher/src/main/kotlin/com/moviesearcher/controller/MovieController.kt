package com.moviesearcher.controller

import com.moviesearcher.dto.response.ApiResponse as MovieApiResponse
import com.moviesearcher.dto.response.MovieDetailDto
import com.moviesearcher.dto.response.MovieSearchResponse
import com.moviesearcher.service.MovieService
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.responses.ApiResponse as SwaggerApiResponse
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema

@Tag(name = "영화 API", description = "영화 검색 및 상세 조회 API")
@RestController
@RequestMapping("/api/v1/movies")
class MovieController(
    private val movieService: MovieService
) {
    
    @Operation(
        summary = "영화 검색",
        description = "query(검색어)로 TMDB에서 영화를 검색합니다.",
        responses = [
            SwaggerApiResponse(
                responseCode = "200",
                description = "검색 결과 반환",
                content = [Content(schema = Schema(implementation = com.moviesearcher.dto.response.MovieSearchResponse::class))]
            )
        ]
    )
    @GetMapping("/search")
    fun searchMovies(
        @Parameter(description = "검색어", required = true)
        @RequestParam query: String,
        @Parameter(description = "페이지 번호", required = false)
        @RequestParam(defaultValue = "1") page: Int
    ): MovieApiResponse<MovieSearchResponse> {
        val result = movieService.searchMovies(query, page)
        return MovieApiResponse(success = true, data = result)
    }
    
    @Operation(
        summary = "영화 상세 조회",
        description = "영화 ID로 TMDB에서 상세 정보를 조회합니다.",
        responses = [
            SwaggerApiResponse(
                responseCode = "200",
                description = "상세 정보 반환",
                content = [Content(schema = Schema(implementation = com.moviesearcher.dto.response.MovieDetailDto::class))]
            )
        ]
    )
    @GetMapping("/{id}")
    fun getMovieDetail(
        @Parameter(description = "영화 ID", required = true)
        @PathVariable id: Long
    ): MovieApiResponse<MovieDetailDto> {
        val result = movieService.getMovieDetail(id)
        return MovieApiResponse(success = true, data = result)
    }
} 