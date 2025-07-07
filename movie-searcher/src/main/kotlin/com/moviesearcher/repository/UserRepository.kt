package com.moviesearcher.repository

import com.moviesearcher.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByName(name: String): User?
    fun existsByName(name: String): Boolean
} 