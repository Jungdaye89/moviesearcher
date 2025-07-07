package com.moviesearcher.controller

import com.moviesearcher.dto.request.SignupRequestDto
import com.moviesearcher.dto.request.LoginRequestDto
import com.moviesearcher.dto.response.SignupResponseDto
import com.moviesearcher.dto.response.LoginResponseDto
import com.moviesearcher.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/signup")
    fun signup(@Valid @RequestBody request: SignupRequestDto): ResponseEntity<SignupResponseDto> {
        val response = authService.signup(request)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody request: LoginRequestDto): ResponseEntity<LoginResponseDto> {
        val response = authService.login(request)
        return ResponseEntity.ok(response)
    }
} 