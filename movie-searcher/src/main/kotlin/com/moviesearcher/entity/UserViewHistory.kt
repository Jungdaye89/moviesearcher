package com.moviesearcher.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "user_view_history")
data class UserViewHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(name = "user_id", nullable = false)
    val userId: Long,
    
    @Column(name = "movie_id", nullable = false)
    val movieId: Long,
    
    @Column(name = "viewed_at")
    val viewedAt: LocalDateTime = LocalDateTime.now(),
    
    @Column(name = "view_duration")
    val viewDuration: Int? = null
) 