package com.moviesearcher.util

import com.moviesearcher.dto.tmdb.*
import com.moviesearcher.dto.response.*

private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

object TmdbMapper {
    fun TmdbMovie.toMovieDto(): MovieDto = MovieDto(
        id = this.id ?: 0L,
        title = this.title ?: "",
        overview = this.overview,
        posterPath = this.posterPath?.takeIf { it.isNotBlank() }?.let { IMAGE_BASE_URL + it },
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )

    fun TmdbMovieDetail.toMovieDetailDto(): MovieDetailDto = MovieDetailDto(
        id = this.id ?: 0L,
        title = this.title ?: "",
        overview = this.overview,
        posterPath = this.posterPath?.takeIf { it.isNotBlank() }?.let { IMAGE_BASE_URL + it },
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        backdropPath = this.backdropPath?.takeIf { it.isNotBlank() }?.let { IMAGE_BASE_URL + it },
        runtime = this.runtime,
        genres = this.genres?.mapNotNull { it.name } ?: emptyList(),
        cast = this.credits?.cast?.map { it.toCastDto() } ?: emptyList(),
        crew = this.credits?.crew?.map { it.toCrewDto() } ?: emptyList()
    )

    fun TmdbCast.toCastDto(): CastDto = CastDto(
        name = this.name ?: "",
        character = this.character,
        profilePath = this.profilePath?.takeIf { it.isNotBlank() }?.let { IMAGE_BASE_URL + it }
    )

    fun TmdbCrew.toCrewDto(): CrewDto = CrewDto(
        name = this.name ?: "",
        job = this.job,
        profilePath = this.profilePath?.takeIf { it.isNotBlank() }?.let { IMAGE_BASE_URL + it }
    )
} 