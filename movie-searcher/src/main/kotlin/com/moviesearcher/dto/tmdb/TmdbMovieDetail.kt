package com.moviesearcher.dto.tmdb

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TmdbMovieDetail(
    val adult: Boolean?,
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
    val popularity: Double?,
    val runtime: Int?,
    val homepage: String?,
    @JsonProperty("original_language")
    val originalLanguage: String?,
    @JsonProperty("original_title")
    val originalTitle: String?,
    val status: String?,
    val tagline: String?,
    val video: Boolean?,
    val budget: Long?,
    val revenue: Long?,
    @JsonProperty("imdb_id")
    val imdbId: String?,
    @JsonProperty("origin_country")
    val originCountry: List<String>?,
    @JsonProperty("belongs_to_collection")
    val belongsToCollection: TmdbCollection?,
    val genres: List<TmdbGenre>?,
    @JsonProperty("production_companies")
    val productionCompanies: List<TmdbProductionCompany>?,
    @JsonProperty("production_countries")
    val productionCountries: List<TmdbProductionCountry>?,
    @JsonProperty("spoken_languages")
    val spokenLanguages: List<TmdbSpokenLanguage>?,
    val credits: TmdbCredits?
) 