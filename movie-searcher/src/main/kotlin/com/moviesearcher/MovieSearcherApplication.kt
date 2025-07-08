package com.moviesearcher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
class MovieSearcherApplication

fun main(args: Array<String>) {
	runApplication<MovieSearcherApplication>(*args)
}