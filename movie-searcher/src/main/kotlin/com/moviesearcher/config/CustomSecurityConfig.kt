package com.moviesearcher.config

import com.moviesearcher.security.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class CustomSecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
) {
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        println(">>> [DEBUG] corsConfigurationSource() 호출됨")
        val configuration = CorsConfiguration()
        configuration.allowedOriginPatterns = listOf("*")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        configuration.allowedHeaders = listOf("*")
        configuration.allowCredentials = true
        configuration.maxAge = 3600L
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean(name = ["customSecurityFilterChain"])
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        println(">>> [DEBUG] filterChain() 호출됨")
        http
            .cors { it.configurationSource(corsConfigurationSource()) }
            .csrf { it.disable() }
            .headers { headers ->
                headers.frameOptions { it.disable() }
                headers.contentTypeOptions { it.disable() }
            }
            .authorizeHttpRequests {
                it.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                it.requestMatchers(
                    "/api/auth/**",
                    "/api/v1/movies",
                    "/api/v1/movies/**",
                    "/actuator/**",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()
                it.anyRequest().authenticated()
            }
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }
} 