package com.moviesearcher.dto.tmdb

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TmdbProductionCountry(
    @JsonProperty("iso_3166_1")
    val iso31661: String?,
    val name: String?
) 