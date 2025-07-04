package com.moviesearcher.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "user_favorites")
data class UserFavorite(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(name = "user_id", nullable = false)
    val userId: Long,
    
    @Column(name = "movie_id", nullable = false)
    val movieId: Long,
    
    @Column
    val folder: String? = null,
    
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()
) 