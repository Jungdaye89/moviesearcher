package com.moviesearcher.service

import com.moviesearcher.dto.request.SignupRequestDto
import com.moviesearcher.dto.request.LoginRequestDto
import com.moviesearcher.dto.response.SignupResponseDto
import com.moviesearcher.dto.response.LoginResponseDto

interface AuthService {
    fun signup(request: SignupRequestDto): SignupResponseDto
    fun login(request: LoginRequestDto): LoginResponseDto
} 