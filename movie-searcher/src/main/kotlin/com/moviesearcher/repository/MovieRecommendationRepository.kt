package com.moviesearcher.repository

import com.moviesearcher.entity.MovieRecommendation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface MovieRecommendationRepository : JpaRepository<MovieRecommendation, Long> {
    fun findByUserIdOrderByScoreDesc(userId: Long, pageable: Pageable): Page<MovieRecommendation>
    
    @Query("SELECT mr FROM MovieRecommendation mr WHERE mr.userId = :userId AND mr.createdAt >= :since ORDER BY mr.score DESC")
    fun findByUserIdAndCreatedAtAfter(@Param("userId") userId: Long, @Param("since") since: LocalDateTime, pageable: Pageable): Page<MovieRecommendation>
    
    fun deleteByUserIdAndMovieId(userId: Long, movieId: Long)
    fun existsByUserIdAndMovieId(userId: Long, movieId: Long): Boolean
} 