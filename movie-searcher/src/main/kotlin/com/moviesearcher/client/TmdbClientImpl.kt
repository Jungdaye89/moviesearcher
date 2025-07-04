package com.moviesearcher.client

import com.moviesearcher.dto.tmdb.TmdbSearchResponse
import com.moviesearcher.dto.tmdb.TmdbMovieDetail
import com.moviesearcher.exception.ExternalApiException
import com.moviesearcher.exception.ErrorCode
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Component
class TmdbClientImpl(
    private val webClient: WebClient,
    @Value("\${tmdb.api.key}") private val apiKey: String
) : TmdbClient {
    override fun searchMovies(query: String, page: Int): TmdbSearchResponse {
        return webClient.get()
            .uri { uriBuilder ->
                uriBuilder
                    .path("/search/movie")
                    .queryParam("api_key", apiKey)
                    .queryParam("query", query)
                    .queryParam("page", page)
                    .queryParam("language", "ko-KR")
                    .build()
            }
            .retrieve()
            .bodyToMono<TmdbSearchResponse>()
            .block() ?: throw ExternalApiException(ErrorCode.SEARCH_RESULT_NOT_FOUND, "검색 결과를 찾을 수 없습니다.")
    }

    override fun getMovieDetail(id: Long): TmdbMovieDetail {
        return webClient.get()
            .uri { uriBuilder ->
                uriBuilder
                    .path("/movie/$id")
                    .queryParam("api_key", apiKey)
                    .queryParam("language", "ko-KR")
                    .queryParam("append_to_response", "credits")
                    .build()
            }
            .retrieve()
            .bodyToMono<TmdbMovieDetail>()
            .block() ?: throw ExternalApiException(ErrorCode.MOVIE_NOT_FOUND, "요청하신 영화를 찾을 수 없습니다.")
    }
}
