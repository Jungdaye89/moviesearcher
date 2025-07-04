# 🎬 MovieSearcher MVP 기획안

## 프로젝트 개요

### 프로젝트명
**MovieSearcher** - TMDB 기반 영화 검색 API 서버

### 프로젝트 목표
- 코틀린과 Spring Boot를 활용한 RESTful API 개발
- TMDB API 연동을 통한 영화 정보 검색 서비스 제공
- 확장 가능한 아키텍처 설계 및 구현

### 핵심 가치 제안
1. **사용자 편의성**: 직관적인 API로 영화 정보를 쉽게 검색
2. **확장성**: 모듈화된 구조로 기능 추가 용이
3. **성능**: 효율적인 비동기 처리
4. **안정성**: 견고한 에러 처리와 로깅

### MVP 범위
- 영화 검색 API (제목 기반)
- 영화 상세 조회 API
- 기본 에러 처리 및 로깅
- Actuator 헬스체크
- API 문서화

## 시스템 아키텍처

### 전체 아키텍처
```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Client App    │    │   Web Browser   │    │   Postman/      │
│   (Mobile/Web)  │◄──►│   (Frontend)    │◄──►│   API Testing   │
└─────────────────┘    └─────────────────┘    └─────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────────┐
│                    MovieSearcher API Server                     │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐            │
│  │   Controller│  │    Service  │  │  External   │            │
│  │     Layer   │◄─►│     Layer   │◄─►│   Client    │            │
│  └─────────────┘  └─────────────┘  └─────────────┘            │
│           │               │               │                    │
│           ▼               ▼               ▼                    │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐            │
│  │   DTO       │  │  Business   │  │   TMDB      │            │
│  │   Models    │  │   Logic     │  │   Models    │            │
│  └─────────────┘  └─────────────┘  └─────────────┘            │
└─────────────────────────────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────────┐
│                        External APIs                            │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │                    TMDB API                             │   │
│  │  - Search Movies: /search/movie                         │   │
│  │  - Movie Details: /movie/{id}                          │   │
│  │  - Movie Credits: /movie/{id}/credits                  │   │
│  └─────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────┘
```

### 아키텍처 패턴
```
1. Layered Architecture (계층형 아키텍처)
   - Controller Layer: HTTP 요청/응답 처리
   - Service Layer: 비즈니스 로직 처리
   - External Client Layer: 외부 API 호출

2. DTO Pattern
   - Request DTO: 클라이언트 요청 데이터
   - Response DTO: 클라이언트 응답 데이터
   - Internal DTO: 내부 서비스 간 데이터 전달

3. Client-Server Pattern
   - RESTful API 설계
   - Stateless 통신
   - JSON 기반 데이터 교환
```

## 기술 스택

### Backend Framework
- **Kotlin 1.9+**: JVM 기반 프로그래밍 언어
- **Spring Boot 3.2+**: 엔터프라이즈 애플리케이션 프레임워크
- **Spring WebFlux**: 비동기 웹 애플리케이션 개발
- **Spring WebClient**: 비동기 HTTP 클라이언트

### Build & Dependency Management
- **Gradle 8.0+**: 빌드 자동화 도구
- **Kotlin DSL**: Gradle 빌드 스크립트

### External APIs
- **TMDB API v3**: 영화 데이터베이스 API
  - Base URL: https://api.themoviedb.org/3
  - Authentication: API Key 방식
  - Rate Limit: 40 requests per 10 seconds

### Development Tools
- **Postman**: API 테스트
- **Git**: 버전 관리

### Logging & Monitoring
- **SLF4J + Logback**: 로깅 프레임워크
- **Spring Boot Actuator**: 애플리케이션 모니터링

## 데이터 모델 설계

### TMDB API 응답 모델(TMDB(외부 API)에서 실제로 받아오는 JSON 구조를 그대로 반영한 데이터 클래스)
// TMDB API에서 받은 응답(JSON)을 역직렬화(deserialize)할 때 사용

```kotlin
// 영화 검색 결과
data class TmdbSearchResponse(
    val page: Int,                    // 현재 페이지 번호
    val results: List<TmdbMovie>,     // 검색된 영화 목록
    val totalPages: Int,              // 전체 페이지 수
    val totalResults: Int             // 전체 검색 결과 수
)

// TMDB 영화 정보
data class TmdbMovie(
    val id: Long,                     // 영화 고유 ID
    val title: String,                // 영화 제목
    val overview: String,             // 영화 줄거리
    val posterPath: String?,          // 포스터 이미지 경로 (없을 수 있음)
    val backdropPath: String?,        // 배경 이미지 경로 (없을 수 있음)
    val releaseDate: String?,         // 개봉일 (미정일 수 있음)
    val voteAverage: Double,          // 평균 평점
    val voteCount: Int,               // 평점 개수
    val popularity: Double            // 인기도 점수
)

// TMDB 영화 상세 정보
data class TmdbMovieDetail(
    val id: Long,                     // 영화 고유 ID
    val title: String,                // 영화 제목
    val overview: String,             // 영화 줄거리
    val posterPath: String?,          // 포스터 이미지 경로 (없을 수 있음)
    val backdropPath: String?,        // 배경 이미지 경로 (없을 수 있음)
    val releaseDate: String?,         // 개봉일 (미정일 수 있음)
    val voteAverage: Double,          // 평균 평점
    val voteCount: Int,               // 평점 개수
    val runtime: Int?,                // 러닝타임 (분, 미정일 수 있음)
    val genres: List<TmdbGenre>,      // 장르 목록
    val credits: TmdbCredits          // 출연진 정보
)

// 장르 정보
data class TmdbGenre(
    val id: Int,                      // 장르 ID
    val name: String                  // 장르명
)

// 출연진 정보
data class TmdbCredits(
    val cast: List<TmdbCast>,         // 배우 목록
    val crew: List<TmdbCrew>          // 제작진 목록
)

// 배우 정보
data class TmdbCast(
    val id: Long,                     // 배우 ID
    val name: String,                 // 배우 이름
    val character: String,            // 캐릭터명
    val profilePath: String?,         // 프로필 이미지 경로 (없을 수 있음)
    val order: Int                    // 출연 순서
)

// 제작진 정보
data class TmdbCrew(
    val id: Long,                     // 제작진 ID
    val name: String,                 // 이름
    val job: String,                  // 직책
    val department: String,           // 부서
    val profilePath: String?          // 프로필 이미지 경로 (없을 수 있음)
)
```

### API 응답 DTO (우리 서비스가 외부에 제공하는 데이터 구조)
// TMDB 응답 모델을 가공/필터링/변환

```kotlin
// 공통 응답 래퍼
data class ApiResponse<T>(
    val success: Boolean,              // 요청 성공 여부
    val data: T? = null,               // 응답 데이터 (에러 시 null)
    val error: ErrorResponse? = null,  // 에러 정보 (성공 시 null)
    val timestamp: LocalDateTime = LocalDateTime.now()  // 응답 시간
)

// 에러 응답
data class ErrorResponse(
    val code: String,                  // 에러 코드
    val message: String,               // 에러 메시지
    val details: String? = null        // 상세 에러 정보 (선택사항)
)

// 영화 검색 응답
data class MovieSearchResponse(
    val movies: List<MovieDto>,        // 검색된 영화 목록
    val pagination: PaginationDto      // 페이지네이션 정보
)

// 영화 정보 DTO
data class MovieDto(
    val id: Long,                      // 영화 고유 ID
    val title: String,                 // 영화 제목
    val overview: String,              // 영화 줄거리
    val posterPath: String?,           // 포스터 이미지 URL (없을 수 있음)
    val releaseDate: LocalDate?,       // 개봉일 (미정일 수 있음)
    val voteAverage: Double,           // 평균 평점
    val voteCount: Int                 // 평점 개수
)

// 영화 상세 정보 DTO
data class MovieDetailDto(
    val id: Long,                      // 영화 고유 ID
    val title: String,                 // 영화 제목
    val overview: String,              // 영화 줄거리
    val posterPath: String?,           // 포스터 이미지 URL (없을 수 있음)
    val backdropPath: String?,         // 배경 이미지 URL (없을 수 있음)
    val releaseDate: LocalDate?,       // 개봉일 (미정일 수 있음)
    val voteAverage: Double,           // 평균 평점
    val voteCount: Int,                // 평점 개수
    val runtime: Int?,                 // 러닝타임 (분, 미정일 수 있음)
    val genres: List<String>,          // 장르 목록
    val cast: List<CastDto>,           // 배우 목록
    val crew: List<CrewDto>            // 제작진 목록
)

// 배우 정보 DTO
data class CastDto(
    val name: String,                  // 배우 이름
    val character: String,             // 캐릭터명
    val profilePath: String?           // 프로필 이미지 URL (없을 수 있음)
)

// 제작진 정보 DTO
data class CrewDto(
    val name: String,                  // 이름
    val job: String,                   // 직책
    val profilePath: String?           // 프로필 이미지 URL (없을 수 있음)
)

// 페이지네이션 DTO
data class PaginationDto(
    val page: Int,                     // 현재 페이지 번호
    val totalPages: Int,               // 전체 페이지 수
    val totalResults: Int,             // 전체 검색 결과 수
    val hasNext: Boolean,              // 다음 페이지 존재 여부
    val hasPrevious: Boolean           // 이전 페이지 존재 여부
)
```

## API 설계 (엔드포인트)

### 1. 영화 검색 API
```
GET /api/v1/movies/search

Query Parameters:
- query: String (검색어, 필수, 최소 1자, 최대 100자)
- page: Int (페이지 번호, 선택, 기본값: 1, 범위: 1-1000)
- language: String (언어, 선택, 기본값: "ko-KR", 지원: "ko-KR", "en-US")

Request Example:
GET /api/v1/movies/search?query=인터스텔라&page=1&language=ko-KR

Response Success (200):
{
  "success": true,
  "data": {
    "movies": [
      {
        "id": 157336,
        "title": "인터스텔라",
        "overview": "우주를 배경으로 한 SF 영화...",
        "posterPath": "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
        "releaseDate": "2014-11-05",
        "voteAverage": 8.4,
        "voteCount": 34567
      }
    ],
    "pagination": {
      "page": 1,
      "totalPages": 5,
      "totalResults": 100,
      "hasNext": true,
      "hasPrevious": false
    }
  },
  "timestamp": "2024-01-15T10:30:00"
}

Response Error (400):
{
  "success": false,
  "error": {
    "code": "INVALID_QUERY",
    "message": "검색어는 1자 이상이어야 합니다.",
    "details": "query parameter is required and must be at least 1 character"
  },
  "timestamp": "2024-01-15T10:30:00"
}
```

### 2. 영화 상세 조회 API
```
GET /api/v1/movies/{id}

Path Parameters:
- id: Long (영화 ID, 필수, TMDB 영화 ID)

Request Example:
GET /api/v1/movies/157336

Response Success (200):
{
  "success": true,
  "data": {
    "id": 157336,
    "title": "인터스텔라",
    "overview": "우주를 배경으로 한 SF 영화...",
    "posterPath": "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
    "backdropPath": "https://image.tmdb.org/t/p/original/nCbkOyOMTEwlEV0LtCOvCnwEONA.jpg",
    "releaseDate": "2014-11-05",
    "voteAverage": 8.4,
    "voteCount": 34567,
    "runtime": 169,
    "genres": ["SF", "드라마", "모험"],
    "cast": [
      {
        "name": "매튜 맥커너히",
        "character": "쿠퍼",
        "profilePath": "https://image.tmdb.org/t/p/w500/..."
      }
    ],
    "crew": [
      {
        "name": "크리스토퍼 놀란",
        "job": "Director",
        "profilePath": "https://image.tmdb.org/t/p/w500/..."
      }
    ]
  },
  "timestamp": "2024-01-15T10:30:00"
}

Response Error (404):
{
  "success": false,
  "error": {
    "code": "MOVIE_NOT_FOUND",
    "message": "해당 영화를 찾을 수 없습니다.",
    "details": "Movie with id 999999 not found in TMDB"
  },
  "timestamp": "2024-01-15T10:30:00"
}
```

## 프로젝트 구조

```
MovieSearcher/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/moviesearcher/
│   │   │       ├── MovieSearcherApplication.kt
│   │   │       ├── controller/
│   │   │       │   └── MovieController.kt
│   │   │       ├── service/
│   │   │       │   ├── MovieService.kt
│   │   │       │   └── TmdbClient.kt
│   │   │       ├── model/
│   │   │       │   ├── dto/
│   │   │       │   │   ├── response/
│   │   │       │   │   │   ├── ApiResponse.kt
│   │   │       │   │   │   ├── MovieDto.kt
│   │   │       │   │   │   ├── MovieDetailDto.kt
│   │   │       │   │   │   └── PaginationDto.kt
│   │   │       │   │   └── ErrorResponse.kt
│   │   │       │   └── tmdb/
│   │   │       │       ├── TmdbSearchResponse.kt
│   │   │       │       ├── TmdbMovie.kt
│   │   │       │       └── TmdbMovieDetail.kt
│   │   │       ├── exception/
│   │   │       │   ├── GlobalExceptionHandler.kt
│   │   │       │   ├── MovieNotFoundException.kt
│   │   │       │   └── TmdbApiException.kt
│   │   │       └── config/
│   │   │           └── TmdbConfig.kt
│   │   └── resources/
│   │       ├── application.yml
│   │       └── application-dev.yml
│   └── test/
│       ├── kotlin/
│       │   └── com/moviesearcher/
│       │       ├── controller/
│       │       │   └── MovieControllerTest.kt
│       │       ├── service/
│       │       │   ├── MovieServiceTest.kt
│       │       │   └── TmdbClientTest.kt
│       │       └── integration/
│       │           └── MovieApiIntegrationTest.kt
│       └── resources/
│           └── application-test.yml
├── docs/
│   ├── 01_MVP_기획안.txt
│   └── 02_고도화_기능.txt
├── build.gradle.kts
├── .gitignore
└── README.md
```

## 설정 파일

### application.yml
```yaml
spring:
  application:
    name: movie-searcher
  profiles:
    active: dev

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
  endpoint:
    health:
      show-details: always

logging:
  level:
    com.moviesearcher: DEBUG
    org.springframework.web: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"

tmdb:
  api:
    key: ${TMDB_API_KEY}
    base-url: https://api.themoviedb.org/3
    image-base-url: https://image.tmdb.org/t/p
    timeout:
      connect: 5000
      read: 10000
  default:
    language: ko-KR
    page-size: 20
```

### application-dev.yml
```yaml
logging:
  level:
    com.moviesearcher: DEBUG
    org.springframework.web: DEBUG
    org.springframework.web.reactive: DEBUG

tmdb:
  api:
    key: ${TMDB_API_KEY:your-dev-api-key}
```

