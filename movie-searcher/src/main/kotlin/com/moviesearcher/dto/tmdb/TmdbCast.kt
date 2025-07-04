package com.moviesearcher.dto.tmdb

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TmdbCast(
    val adult: Boolean?,
    val gender: Int?,
    val id: Long?,
    @JsonProperty("known_for_department")
    val knownForDepartment: String?,
    val name: String?,
    @JsonProperty("original_name")
    val originalName: String?,
    val popularity: Double?,
    @JsonProperty("profile_path")
    val profilePath: String?,
    @JsonProperty("cast_id")
    val castId: Int?,
    val character: String?,
    @JsonProperty("credit_id")
    val creditId: String?,
    val order: Int?
) 