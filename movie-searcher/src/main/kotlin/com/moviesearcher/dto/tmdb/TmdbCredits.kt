package com.moviesearcher.dto.tmdb

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TmdbCredits(
    @JsonProperty("cast")
    val cast: List<TmdbCast>?,
    @JsonProperty("crew")
    val crew: List<TmdbCrew>?
) 