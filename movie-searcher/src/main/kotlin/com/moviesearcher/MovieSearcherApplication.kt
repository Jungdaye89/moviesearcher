package com.moviesearcher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovieSearcherApplication

fun main(args: Array<String>) {
    runApplication<MovieSearcherApplication>(*args)
}