package com.moviesearcher.service

import com.moviesearcher.dto.response.MovieSearchResponse
import com.moviesearcher.dto.response.MovieDetailDto

interface MovieService {
    fun searchMovies(query: String, page: Int): MovieSearchResponse
    fun getMovieDetail(id: Long): MovieDetailDto
}
