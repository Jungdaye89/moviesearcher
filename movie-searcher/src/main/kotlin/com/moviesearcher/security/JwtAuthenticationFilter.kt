package com.moviesearcher.security

import com.moviesearcher.util.JwtProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtProvider: JwtProvider
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // SecurityConfig와 완전히 일치하는 permitAll 경로만 남김
        val permitAllPaths = listOf(
            "/api/auth", "/api/v1/movies", "/actuator", "/swagger-ui", "/v3/api-docs"
        )
        val uri = request.requestURI
        val isPermitAllPath = permitAllPaths.any { path ->
            uri == path || uri.startsWith("$path/")
        }
        if (isPermitAllPath) {
            println("[JWT FILTER] permitAll 통과: $uri")
            filterChain.doFilter(request, response)
            return
        }
        val token = resolveToken(request)
        if (token != null && jwtProvider.validateToken(token)) {
            println("[JWT FILTER] JWT 인증 성공: $uri, userName=${jwtProvider.getUserName(token)}")
            val userName = jwtProvider.getUserName(token)
            val principal = User(userName, "", emptyList())
            val authentication = UsernamePasswordAuthenticationToken(principal, null, principal.authorities)
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication
        } else {
            println("[JWT FILTER] JWT 인증 없음/실패: $uri")
        }
        filterChain.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearer = request.getHeader("Authorization") ?: return null
        return if (bearer.startsWith("Bearer ")) bearer.substring(7) else null
    }
} 