package com.moviesearcher.dto.tmdb

import com.fasterxml.jackson.annotation.JsonProperty

data class TmdbSearchResponse(
    val page: Int?,
    val results: List<TmdbMovie>?,
    @JsonProperty("total_pages")
    val totalPages: Int?,
    @JsonProperty("total_results")
    val totalResults: Int?
) 