package com.moviesearcher.util

import com.moviesearcher.config.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtProvider(
    private val jwtProperties: JwtProperties
) {
    fun generateToken(userId: Long, name: String): String {
        val now = Date()
        val expiry = Date(now.time + jwtProperties.expiration)
        return Jwts.builder()
            .setSubject(userId.toString())
            .claim("name", name)
            .setIssuedAt(now)
            .setExpiration(expiry)
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secret.toByteArray())
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            val claims = getClaims(token)
            !claims.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }

    fun getUserId(token: String): Long {
        return getClaims(token).subject.toLong()
    }

    fun getUserName(token: String): String {
        return getClaims(token)["name"] as String
    }

    private fun getClaims(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(jwtProperties.secret.toByteArray())
            .parseClaimsJws(token)
            .body
    }
} 