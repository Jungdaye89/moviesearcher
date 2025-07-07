package com.moviesearcher.exception

import com.moviesearcher.dto.response.ApiResponse
import com.moviesearcher.dto.response.ErrorResponse
import com.moviesearcher.exception.ExternalApiException
import com.moviesearcher.exception.AuthException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.resource.NoResourceFoundException
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MovieNotFoundException::class)
    fun handleMovieNotFoundException(ex: MovieNotFoundException, request: WebRequest): ResponseEntity<ApiResponse<Nothing>> {
        val errorResponse = ErrorResponse(
            code = ex.errorCode.code,
            message = ex.message ?: ex.errorCode.message,
            details = "영화 ID: ${ex.movieId}"
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ApiResponse(success = false, error = errorResponse))
    }

    @ExceptionHandler(InvalidSearchQueryException::class)
    fun handleInvalidSearchQueryException(ex: InvalidSearchQueryException, request: WebRequest): ResponseEntity<ApiResponse<Nothing>> {
        val errorResponse = ErrorResponse(
            code = ex.errorCode.code,
            message = ex.message ?: ex.errorCode.message,
            details = "검색어: ${ex.query}"
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse(success = false, error = errorResponse))
    }

    @ExceptionHandler(ExternalApiException::class)
    fun handleExternalApiException(ex: ExternalApiException, request: WebRequest): ResponseEntity<ApiResponse<Nothing>> {
        val errorResponse = ErrorResponse(
            code = ex.errorCode.code,
            message = ex.message ?: "외부 API 오류"
        )
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ApiResponse.error(errorResponse))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException, request: WebRequest): ResponseEntity<ApiResponse<Nothing>> {
        val errorResponse = ErrorResponse(
            code = ErrorCode.INVALID_SEARCH_QUERY.code,
            message = ErrorCode.INVALID_SEARCH_QUERY.message,
            details = ex.message
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse(success = false, error = errorResponse))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<ApiResponse<Nothing>> {
        println("[DEBUG] MethodArgumentNotValidException: ${ex.message}")
        val errorResponse = ErrorResponse(
            code = "VALIDATION_ERROR",
            message = ex.bindingResult.allErrors.firstOrNull()?.defaultMessage ?: "유효성 검사 실패",
            details = ex.bindingResult.toString()
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse(success = false, error = errorResponse))
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleNotReadableException(ex: HttpMessageNotReadableException, request: WebRequest): ResponseEntity<ApiResponse<Nothing>> {
        println("[DEBUG] HttpMessageNotReadableException: ${ex.message}")
        val errorResponse = ErrorResponse(
            code = "JSON_PARSE_ERROR",
            message = "요청 본문을 읽을 수 없습니다.",
            details = ex.message
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse(success = false, error = errorResponse))
    }

    @ExceptionHandler(BindException::class)
    fun handleBindException(ex: BindException, request: WebRequest): ResponseEntity<ApiResponse<Nothing>> {
        println("[DEBUG] BindException: ${ex.message}")
        val errorResponse = ErrorResponse(
            code = "BIND_ERROR",
            message = ex.bindingResult.allErrors.firstOrNull()?.defaultMessage ?: "바인딩 실패",
            details = ex.bindingResult.toString()
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse(success = false, error = errorResponse))
    }

    @ExceptionHandler(AuthException::class)
    fun handleAuthException(ex: AuthException, request: WebRequest): ResponseEntity<ApiResponse<Nothing>> {
        val errorResponse = ErrorResponse(
            code = ex.errorCode.code,
            message = ex.errorCode.message,
            details = ex.message
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse(success = false, error = errorResponse))
    }

    // 403 Forbidden 에러 처리 추가
    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException(ex: AccessDeniedException, request: WebRequest): ResponseEntity<ApiResponse<Nothing>> {
        println("[DEBUG] AccessDeniedException: ${ex.message}")
        val errorResponse = ErrorResponse(
            code = "FORBIDDEN",
            message = "접근 권한이 없습니다.",
            details = ex.message
        )
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(ApiResponse(success = false, error = errorResponse))
    }

    // 인증 실패 에러 처리 추가
    @ExceptionHandler(AuthenticationException::class)
    fun handleAuthenticationException(ex: AuthenticationException, request: WebRequest): ResponseEntity<ApiResponse<Nothing>> {
        println("[DEBUG] AuthenticationException: ${ex.message}")
        val errorResponse = ErrorResponse(
            code = "UNAUTHORIZED",
            message = "인증이 필요합니다.",
            details = ex.message
        )
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ApiResponse(success = false, error = errorResponse))
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNoHandlerFoundException(ex: NoHandlerFoundException, request: WebRequest): ResponseEntity<ApiResponse<Nothing>> {
        val errorResponse = ErrorResponse(
            code = "NOT_FOUND",
            message = "요청하신 리소스를 찾을 수 없습니다.",
            details = ex.message
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ApiResponse(success = false, error = errorResponse))
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleNoResourceFoundException(ex: NoResourceFoundException, request: WebRequest): ResponseEntity<ApiResponse<Nothing>> {
        val errorResponse = ErrorResponse(
            code = "NOT_FOUND",
            message = "정적 리소스를 찾을 수 없습니다.",
            details = ex.message
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ApiResponse(success = false, error = errorResponse))
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception, request: WebRequest): ResponseEntity<ApiResponse<Nothing>> {
        println("[DEBUG] Generic Exception: ${ex.message}")
        val errorResponse = ErrorResponse(
            code = ErrorCode.INTERNAL_SERVER_ERROR.code,
            message = ErrorCode.INTERNAL_SERVER_ERROR.message,
            details = ex.message
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ApiResponse(success = false, error = errorResponse))
    }
} 