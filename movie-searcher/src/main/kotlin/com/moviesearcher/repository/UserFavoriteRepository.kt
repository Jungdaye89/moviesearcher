package com.moviesearcher.repository

import com.moviesearcher.entity.UserFavorite
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserFavoriteRepository : JpaRepository<UserFavorite, Long> {
    fun findByUserId(userId: Long, pageable: Pageable): Page<UserFavorite>
    fun findByUserIdAndMovieId(userId: Long, movieId: Long): UserFavorite?
    fun existsByUserIdAndMovieId(userId: Long, movieId: Long): Boolean
    fun deleteByUserIdAndMovieId(userId: Long, movieId: Long)
    
    @Query("SELECT COUNT(uf) FROM UserFavorite uf WHERE uf.userId = :userId")
    fun countByUserId(@Param("userId") userId: Long): Long
    
    @Query("SELECT uf.folder, COUNT(uf) FROM UserFavorite uf WHERE uf.userId = :userId AND uf.folder IS NOT NULL GROUP BY uf.folder")
    fun getFolderStatistics(@Param("userId") userId: Long): List<Array<Any>>
} 