package com.moviesearcher.client

import com.moviesearcher.dto.tmdb.TmdbSearchResponse
import com.moviesearcher.dto.tmdb.TmdbMovieDetail

interface TmdbClient {
    fun searchMovies(query: String, page: Int): TmdbSearchResponse
    fun getMovieDetail(id: Long): TmdbMovieDetail
} 