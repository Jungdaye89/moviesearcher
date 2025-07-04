# MovieSearcher MVP 구현 스크립트

## MVP 구현 단계

### 1단계: 프로젝트 초기 설정
#### 1.1 스프링 부트 프로젝트 생성
- [X] 프로젝트 설정
- [X] Dependencies 추가
  - [X] Spring Web
  - [X] Spring Boot Actuator
  - [X] Spring Boot DevTools

#### 1.2 프로젝트 구조 설정
- [X] 기본 패키지 구조 생성
  - [X] `src/main/kotlin/com/moviesearcher/`
  - [X] `src/main/kotlin/com/moviesearcher/controller/`
  - [X] `src/main/kotlin/com/moviesearcher/service/`
  - [X] `src/main/kotlin/com/moviesearcher/client/`
  - [X] `src/main/kotlin/com/moviesearcher/dto/`
  - [X] `src/main/kotlin/com/moviesearcher/config/`
  - [X] `src/main/kotlin/com/moviesearcher/exception/`
- [X] `src/test/kotlin/` 테스트 패키지 구조도 동일하게 생성

#### 1.3 설정 파일 작성
- [X] `application.yml` 파일 생성
  - [X] TMDB API 설정
    - [X] `tmdb.api.base-url`: https://api.themoviedb.org/3
    - [X] `tmdb.api.key`: TMDB API 키 (환경변수로 관리)
    - [X] `tmdb.api.image-base-url`: https://image.tmdb.org/t/p/w500
  - [X] 서버 설정
    - [X] `server.port`: 8080
  - [X] 로깅 설정
    - [X] 로그 레벨 설정
- [X] `application-dev.yml` 개발 환경 설정 파일 생성
- [X] `application-prod.yml` 운영 환경 설정 파일 생성

### 2단계: 데이터 모델 및 DTO 구현

#### 2.1 TMDB API 응답 모델 구현

- [X] `src/main/kotlin/com/moviesearcher/dto/tmdb/` 패키지 생성
- [X] `TmdbSearchResponse.kt` 파일 생성
  - [X] `page`, `results`, `totalPages`, `totalResults` 필드 구현
- [X] `TmdbMovie.kt` 파일 생성
  - [X] `id`, `title`, `overview`, `posterPath`, `backdropPath`, `releaseDate`, `voteAverage`, `voteCount`, `popularity` 필드 구현
  - [X] nullable 필드들에 `?` 추가
- [X] `TmdbMovieDetail.kt` 파일 생성
  - [X] `TmdbMovie` 상속 또는 별도 클래스로 구현
  - [X] `runtime`, `genres`, `credits` 필드 추가
- [X] `TmdbGenre.kt` 파일 생성
  - [X] `id`, `name` 필드 구현
- [X] `TmdbCredits.kt` 파일 생성
  - [X] `cast`, `crew` 필드 구현
- [X] `TmdbCast.kt` 파일 생성
  - [X] `id`, `name`, `character`, `profilePath`, `order` 필드 구현
- [X] `TmdbCrew.kt` 파일 생성
  - [X] `id`, `name`, `job`, `department`, `profilePath` 필드 구현

#### 2.2 API 응답 DTO 구현
- [X] `src/main/kotlin/com/moviesearcher/dto/response/` 패키지 생성
- [X] `ApiResponse.kt` 파일 생성
  - [X] 제네릭 타입 `T`를 사용한 공통 응답 래퍼 구현
  - [X] `success`, `data`, `error`, `timestamp` 필드 구현
- [X] `ErrorResponse.kt` 파일 생성
  - [X] `code`, `message`, `details` 필드 구현
- [X] `MovieSearchResponse.kt` 파일 생성
  - [X] `movies`, `pagination` 필드 구현
- [X] `MovieDto.kt` 파일 생성
  - [X] `id`, `title`, `overview`, `posterPath`, `releaseDate`, `voteAverage`, `voteCount` 필드 구현
- [X] `MovieDetailDto.kt` 파일 생성
  - [X] `MovieDto` 상속 또는 별도 클래스로 구현
  - [X] `backdropPath`, `runtime`, `genres`, `cast`, `crew` 필드 추가
- [X] `CastDto.kt` 파일 생성
  - [X] `name`, `character`, `profilePath` 필드 구현
- [X] `CrewDto.kt` 파일 생성
  - [X] `name`, `job`, `profilePath` 필드 구현
- [X] `PaginationDto.kt` 파일 생성
  - [X] `page`, `totalPages`, `totalResults`, `hasNext`, `hasPrevious` 필드 구현

### 3단계: TMDB API 클라이언트 구현

#### 3.1 TMDB API 클라이언트 인터페이스 정의
- [X] `src/main/kotlin/com/moviesearcher/client/` 패키지 생성
- [X] `TmdbClient.kt` 인터페이스 생성
  - [X] `searchMovies(query: String, page: Int): TmdbSearchResponse` 메서드 정의
  - [X] `getMovieDetail(id: Long): TmdbMovieDetail` 메서드 정의

#### 3.2 TMDB API 클라이언트 구현
- [X] `TmdbClientImpl.kt` 클래스 생성
  - [X] `@Component` 어노테이션 추가
  - [X] `WebClient` 주입
  - [X] TMDB API 설정값 주입 (`@Value` 사용)
  - [X] `searchMovies` 메서드 구현
    - [X] `/search/movie` 엔드포인트 호출
    - [X] 쿼리 파라미터 설정
    - [X] 응답을 `TmdbSearchResponse`로 변환
  - [X] `getMovieDetail` 메서드 구현
    - [X] `/movie/{id}` 엔드포인트 호출
    - [X] credits 포함하여 호출
    - [X] 응답을 `TmdbMovieDetail`로 변환

#### 3.3 WebClient 설정
- [X] `src/main/kotlin/com/moviesearcher/config/` 패키지 생성
- [X] `WebClientConfig.kt` 파일 생성
  - [X] `@Configuration` 어노테이션 추가
  - [X] `WebClient` 빈 생성
  - [X] 기본 헤더 설정 (User-Agent 등)
  - [X] maxInMemorySize(버퍼 크기) 설정
  - [X] 에러 핸들링 설정

### 4단계: 서비스 레이어 구현

#### 4.1 영화 검색 서비스 인터페이스
- [X] `src/main/kotlin/com/moviesearcher/service/` 패키지 생성
- [X] `MovieService.kt` 인터페이스 생성
  - [X] `searchMovies(query: String, page: Int): MovieSearchResponse` 메서드 정의
  - [X] `getMovieDetail(id: Long): MovieDetailDto` 메서드 정의

#### 4.2 영화 검색 서비스 구현
- [X] `MovieServiceImpl.kt` 클래스 생성
  - [X] `@Service` 어노테이션 추가
  - [X] `TmdbClient` 주입
  - [X] `searchMovies` 메서드 구현
    - [X] 입력값 검증 (query 길이, page 범위 등)
    - [X] TMDB API 호출
    - [X] 응답 데이터를 DTO로 변환
    - [X] 이미지 URL 완성 (base URL + poster path)
    - [X] 페이지네이션 정보 생성
  - [X] `getMovieDetail` 메서드 구현
    - [X] 영화 ID 검증
    - [X] TMDB API 호출
    - [X] 응답 데이터를 DTO로 변환
    - [X] 이미지 URL 완성
    - [X] 장르, 출연진 정보 변환

#### 4.3 데이터 변환 유틸리티
- [X] `src/main/kotlin/com/moviesearcher/util/` 패키지 생성
- [X] `TmdbMapper.kt` 파일 생성
  - [X] TMDB 응답을 DTO로 변환하는 확장 함수들 구현
  - [X] `TmdbMovie.toMovieDto()` 확장 함수
  - [X] `TmdbMovieDetail.toMovieDetailDto()` 확장 함수
  - [X] `TmdbCast.toCastDto()` 확장 함수
  - [X] `TmdbCrew.toCrewDto()` 확장 함수
  - [X] 이미지 URL 완성 함수 구현

### 5단계: 컨트롤러 레이어 구현

#### 5.1 영화 컨트롤러 구현
- [X] `src/main/kotlin/com/moviesearcher/controller/` 패키지 생성
- [X] `MovieController.kt` 파일 생성
  - [X] `@RestController` 어노테이션 추가
  - [X] `@RequestMapping("/api/v1/movies")` 추가
  - [X] `MovieService` 주입
- [X] 영화 검색 API 구현
  - [X] `@GetMapping("/search")` 메서드 생성
  - [X] `@RequestParam`으로 query, page 받기
  - [X] 기본값 설정 (page=1)
  - [X] `MovieService.searchMovies` 호출
  - [X] `ApiResponse`로 래핑하여 반환
- [X] 영화 상세 조회 API 구현
  - [X] `@GetMapping("/{id}")` 메서드 생성
  - [X] `@PathVariable`로 영화 ID 받기
  - [X] `MovieService.getMovieDetail` 호출
  - [X] `ApiResponse`로 래핑하여 반환

### 6단계: 예외 처리 및 검증

#### 6.1 커스텀 예외 클래스 구현
- [X] `src/main/kotlin/com/moviesearcher/exception/` 패키지 생성
- [X] `MovieNotFoundException.kt` 파일 생성
  - [X] 영화를 찾을 수 없는 경우 예외
- [X] `InvalidSearchQueryException.kt` 파일 생성
  - [X] 검색어가 유효하지 않은 경우 예외
- [X] `ServiceUnavailableException.kt` 파일 생성
  - [X] TMDB API 호출 실패 시 예외

#### 6.2 글로벌 예외 핸들러 구현
- [X] `GlobalExceptionHandler.kt` 파일 생성
  - [X] `@RestControllerAdvice` 어노테이션 추가
  - [X] `MovieNotFoundException` 처리
  - [X] `InvalidSearchQueryException` 처리
  - [X] `ServiceUnavailableException` 처리
  - [X] 일반적인 `Exception` 처리
  - [X] 각 예외에 맞는 HTTP 상태 코드 반환
  - [X] `ApiResponse` 형태로 에러 응답 반환

#### 6.3 입력값 검증
- [X] 검색어 길이 검증 (1-100자)
- [X] 페이지 번호 범위 검증 (1-1000)
- [X] 영화 ID 유효성 검증

### 7단계: Spring Boot Actuator 설정

#### 7.1 Actuator 엔드포인트 설정
- [X] `application.yml`에 Actuator 설정 추가
  - [X] `management.endpoints.web.exposure.include`: health,info,metrics
  - [X] `management.endpoint.health.show-details`: when-authorized

### 8단계: 테스트 구현

#### 8.1 단위 테스트 구현
- [ ] `MovieServiceTest.kt` 파일 생성
  - [ ] 정상 검색 요청 처리 테스트
  - [ ] 입력값 검증 예외 처리 테스트
  - [ ] 영화 상세 조회 테스트
  - [ ] MockK를 사용한 의존성 모킹
- [ ] `MovieControllerTest.kt` 파일 생성
  - [ ] REST API 엔드포인트 테스트
  - [ ] MockMvc를 사용한 HTTP 요청 테스트
  - [ ] 성공/실패 응답 검증
- [ ] `TmdbClientTest.kt` 파일 생성
  - [ ] WebClient 모킹 테스트
  - [ ] API 호출 파라미터 검증
  - [ ] 응답 데이터 변환 테스트

#### 8.2 통합 테스트 구현
- [ ] `MovieControllerIntegrationTest.kt` 파일 생성
  - [ ] @SpringBootTest를 사용한 전체 컨텍스트 테스트
  - [ ] 입력값 검증 에러 처리 테스트
  - [ ] Actuator 엔드포인트 동작 확인

#### 8.3 테스트 환경 설정
- [ ] `application-test.yml` 파일 생성
- [ ] MockK 의존성 추가 (build.gradle.kts)
- [ ] 테스트 실행 가이드 작성 (TEST_README.md)

### 9단계: 문서화 및 배포 준비

#### 9.1 API 문서화
- [ ] Swagger/OpenAPI 설정
  - [ ] `springdoc-openapi-starter-webmvc-ui` 의존성 추가
  - [ ] `OpenApiConfig.kt` 설정 파일 생성
  - [ ] API 엔드포인트에 `@Operation` 어노테이션 추가
  - [ ] DTO 클래스에 `@Schema` 어노테이션 추가

#### 9.2 README 작성
- [ ] 프로젝트 소개
- [ ] 기술 스택
- [ ] 설치 및 실행 방법
- [ ] API 사용법
- [ ] 환경 설정
- [ ] 테스트 실행 방법

#### 9.3 배포 준비
- [x] `Dockerfile` 작성  
  → OpenJDK 17 기반, JAR 및 application.yml 복사, ENTRYPOINT에서 명시적 yml 지정
- [x] `.dockerignore` 파일 생성  
  → (필요시 추가, 현재는 미작성)
- [x] `docker-compose.yml` 작성 (선택사항)  
  → (단일 컨테이너 배포로 생략)
- [x] 환경변수 설정 가이드 작성  
  → application.yml에 TMDB API KEY 포함, Dockerfile에서 복사 및 적용

### 10단계: 최종 테스트 및 검증

#### 10.1 기능 테스트
- [ ] 영화 검색 기능 테스트
  - [ ] 다양한 검색어로 테스트
  - [ ] 페이지네이션 테스트
  - [ ] 언어 변경 테스트
- [ ] 영화 상세 조회 기능 테스트
  - [ ] 존재하는 영화 ID로 테스트
  - [ ] 존재하지 않는 영화 ID로 테스트
- [ ] 에러 처리 테스트
  - [ ] 잘못된 검색어 입력
  - [ ] 잘못된 페이지 번호 입력
  - [ ] 네트워크 오류 상황

#### 10.2 성능 테스트
- [ ] 응답 시간 측정
- [ ] 동시 요청 처리 테스트
- [ ] 메모리 사용량 확인

#### 10.3 보안 테스트
- [ ] API 키 노출 방지 확인
- [ ] 입력값 검증 확인
- [ ] CORS 설정 확인

## 완료 기준
- [ ] 영화 검색 API가 정상 동작
- [ ] 영화 상세 조회 API가 정상 동작
- [ ] 모든 예외 상황이 적절히 처리됨
- [ ] 단위 테스트 및 통합 테스트 통과
- [ ] API 문서가 완성됨
- [ ] README가 완성됨
- [ ] Docker 컨테이너로 실행 가능 