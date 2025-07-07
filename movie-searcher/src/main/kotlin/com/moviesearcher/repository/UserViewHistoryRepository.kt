package com.moviesearcher.repository

import com.moviesearcher.entity.UserViewHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserViewHistoryRepository : JpaRepository<UserViewHistory, Long> {
    fun findByUserIdOrderByViewedAtDesc(userId: Long): List<UserViewHistory>
    fun findByMovieId(movieId: Long): List<UserViewHistory>
} 