package com.moviesearcher.exception

class MovieNotFoundException(
    movieId: Long,
    message: String = ErrorCode.MOVIE_NOT_FOUND.message
) : RuntimeException(message) {
    val errorCode = ErrorCode.MOVIE_NOT_FOUND
    val movieId = movieId
} 