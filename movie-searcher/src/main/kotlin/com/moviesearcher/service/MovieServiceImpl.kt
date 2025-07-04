package com.moviesearcher.service

import com.moviesearcher.client.TmdbClient
import com.moviesearcher.dto.response.*
import com.moviesearcher.exception.InvalidSearchQueryException
import com.moviesearcher.exception.MovieNotFoundException
import com.moviesearcher.util.TmdbMapper.toMovieDetailDto
import com.moviesearcher.util.TmdbMapper.toMovieDto
import org.springframework.stereotype.Service

@Service
class MovieServiceImpl(
    private val tmdbClient: TmdbClient
) : MovieService {
    override fun searchMovies(query: String, page: Int): MovieSearchResponse {
        if (query.isBlank() || query.length > 100) {
            throw InvalidSearchQueryException(query, "검색어는 1~100자여야 합니다.")
        }
        require(page in 1..1000) { "페이지 번호는 1~1000 사이여야 합니다." }
        val tmdbResponse = tmdbClient.searchMovies(query, page)
        val movies = tmdbResponse.results?.map { it.toMovieDto() } ?: emptyList()
        val pagination = PaginationDto(
            page = tmdbResponse.page ?: 1,
            totalPages = tmdbResponse.totalPages ?: 1,
            totalResults = tmdbResponse.totalResults ?: 0,
            hasNext = (tmdbResponse.page ?: 1) < (tmdbResponse.totalPages ?: 1),
            hasPrevious = (tmdbResponse.page ?: 1) > 1
        )
        return MovieSearchResponse(movies, pagination)
    }

    override fun getMovieDetail(id: Long): MovieDetailDto {
        if (id <= 0) {
            throw MovieNotFoundException(id, "영화 ID는 0보다 커야 합니다.")
        }
        val tmdbDetail = tmdbClient.getMovieDetail(id)
        return tmdbDetail.toMovieDetailDto()
    }
}
