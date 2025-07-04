package com.moviesearcher.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.ExchangeStrategies

@Configuration
class WebClientConfig {
    @Bean
    fun webClient(@Value("\${tmdb.api.base-url}") baseUrl: String): WebClient {
        val exchangeStrategies = ExchangeStrategies.builder()
            .codecs { config ->
                config.defaultCodecs().maxInMemorySize(10 * 1024 * 1024) // 10MB
            }
            .build()

        return WebClient.builder()
            .baseUrl(baseUrl)
            .exchangeStrategies(exchangeStrategies)
            .defaultHeader("User-Agent", "MovieSearcher/1.0")
            .build()
    }
}
