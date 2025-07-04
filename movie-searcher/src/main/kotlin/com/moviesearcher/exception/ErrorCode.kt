package com.moviesearcher.exception

enum class ErrorCode(val code: String, val message: String) {
    // 영화 검색 관련 에러
    MOVIE_NOT_FOUND("MOVIE_001", "요청하신 영화를 찾을 수 없습니다."),
    SEARCH_RESULT_NOT_FOUND("SEARCH_001", "검색 결과를 찾을 수 없습니다."),
    
    // 입력값 검증 에러
    INVALID_SEARCH_QUERY("VALIDATION_001", "검색어를 올바르게 입력해주세요."),
    INVALID_PAGE_NUMBER("VALIDATION_002", "페이지 번호를 올바르게 입력해주세요."),
    INVALID_MOVIE_ID("VALIDATION_003", "영화 ID를 올바르게 입력해주세요."),
    
    // 인증 관련 에러
    USERNAME_ALREADY_EXISTS("AUTH_001", "이미 존재하는 이름입니다."),
    INVALID_CREDENTIALS("AUTH_002", "이름 또는 비밀번호가 올바르지 않습니다."),
    USER_NOT_FOUND("AUTH_003", "사용자를 찾을 수 없습니다."),
    INVALID_TOKEN("AUTH_004", "유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED("AUTH_005", "만료된 토큰입니다."),
    
    // 서비스 에러
    SERVICE_UNAVAILABLE("SERVICE_001", "서비스를 일시적으로 이용할 수 없습니다."),
    INTERNAL_SERVER_ERROR("SERVER_001", "서버 오류가 발생했습니다.")
} 