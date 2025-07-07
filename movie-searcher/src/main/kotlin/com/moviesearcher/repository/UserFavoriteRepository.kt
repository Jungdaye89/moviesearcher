package com.moviesearcher.repository

import com.moviesearcher.entity.UserFavorite
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserFavoriteRepository : JpaRepository<UserFavorite, Long> {
    fun findByUserId(userId: Long): List<UserFavorite>
    fun findByUserIdAndMovieId(userId: Long, movieId: Long): UserFavorite?
    fun existsByUserIdAndMovieId(userId: Long, movieId: Long): Boolean
} 