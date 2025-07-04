package com.moviesearcher.dto.tmdb

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TmdbProductionCompany(
    val id: Long?,
    @JsonProperty("logo_path")
    val logoPath: String?,
    val name: String?,
    @JsonProperty("origin_country")
    val originCountry: String?
) 