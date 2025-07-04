package com.moviesearcher.dto.tmdb

import com.fasterxml.jackson.annotation.JsonProperty

data class TmdbMovie(
    val id: Long?,
    val title: String?,
    val overview: String?,
    @JsonProperty("poster_path")
    val posterPath: String?,
    @JsonProperty("backdrop_path")
    val backdropPath: String?,
    @JsonProperty("release_date")
    val releaseDate: String?,
    @JsonProperty("vote_average")
    val voteAverage: Double?,
    @JsonProperty("vote_count")
    val voteCount: Int?,
    val popularity: Double?
) 