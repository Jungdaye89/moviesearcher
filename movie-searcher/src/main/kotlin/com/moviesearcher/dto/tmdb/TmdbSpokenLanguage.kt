package com.moviesearcher.dto.tmdb

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TmdbSpokenLanguage(
    @JsonProperty("english_name")
    val englishName: String?,
    @JsonProperty("iso_639_1")
    val iso6391: String?,
    val name: String?
) 