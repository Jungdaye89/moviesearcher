package com.moviesearcher.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "movie_recommendations")
data class MovieRecommendation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(name = "user_id", nullable = false)
    val userId: Long,
    
    @Column(name = "movie_id", nullable = false)
    val movieId: Long,
    
    @Column(nullable = false, precision = 3, scale = 2)
    val score: BigDecimal,
    
    @Column
    val reason: String? = null,
    
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()
) 