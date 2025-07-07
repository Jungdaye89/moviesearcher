package com.moviesearcher.service

import com.moviesearcher.dto.request.SignupRequestDto
import com.moviesearcher.dto.request.LoginRequestDto
import com.moviesearcher.dto.response.SignupResponseDto
import com.moviesearcher.dto.response.LoginResponseDto
import com.moviesearcher.entity.User
import com.moviesearcher.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.moviesearcher.exception.AuthException
import com.moviesearcher.exception.ErrorCode
import com.moviesearcher.util.JwtProvider

@Service
class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider
) : AuthService {
    private val passwordEncoder = BCryptPasswordEncoder()

    @Transactional
    override fun signup(request: SignupRequestDto): SignupResponseDto {
        if (userRepository.existsByName(request.name)) {
            throw AuthException(ErrorCode.AUTH_DUPLICATE_USER)
        }
        val encodedPassword = passwordEncoder.encode(request.password)
        val user = User(
            name = request.name,
            password = encodedPassword
        )
        val saved = userRepository.save(user)
        return SignupResponseDto(
            id = saved.id,
            name = saved.name,
            createdAt = saved.createdAt
        )
    }

    @Transactional(readOnly = true)
    override fun login(request: LoginRequestDto): LoginResponseDto {
        val user = userRepository.findByName(request.name)
            ?: throw AuthException(ErrorCode.AUTH_INVALID_CREDENTIALS)
        if (!passwordEncoder.matches(request.password, user.password)) {
            throw AuthException(ErrorCode.AUTH_INVALID_CREDENTIALS)
        }
        val token = jwtProvider.generateToken(user.id, user.name)
        return LoginResponseDto(
            token = token,
            userId = user.id,
            name = user.name
        )
    }
} 