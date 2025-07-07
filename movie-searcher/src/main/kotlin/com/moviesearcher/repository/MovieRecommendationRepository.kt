package com.moviesearcher.repository

import com.moviesearcher.entity.MovieRecommendation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRecommendationRepository : JpaRepository<MovieRecommendation, Long> {
    fun findByUserIdOrderByScoreDesc(userId: Long): List<MovieRecommendation>
    fun findByMovieId(movieId: Long): List<MovieRecommendation>
} 