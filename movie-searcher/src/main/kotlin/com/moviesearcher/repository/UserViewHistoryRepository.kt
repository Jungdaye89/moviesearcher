package com.moviesearcher.repository

import com.moviesearcher.entity.UserViewHistory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface UserViewHistoryRepository : JpaRepository<UserViewHistory, Long> {
    fun findByUserIdOrderByViewedAtDesc(userId: Long, pageable: Pageable): Page<UserViewHistory>
    
    @Query("SELECT uvh.movieId, COUNT(uvh) FROM UserViewHistory uvh WHERE uvh.userId = :userId GROUP BY uvh.movieId ORDER BY COUNT(uvh) DESC")
    fun findMostViewedMoviesByUserId(@Param("userId") userId: Long, pageable: Pageable): List<Array<Any>>
    
    @Query("SELECT uvh FROM UserViewHistory uvh WHERE uvh.userId = :userId AND uvh.viewedAt >= :since ORDER BY uvh.viewedAt DESC")
    fun findByUserIdAndViewedAtAfter(@Param("userId") userId: Long, @Param("since") since: LocalDateTime, pageable: Pageable): Page<UserViewHistory>
} 