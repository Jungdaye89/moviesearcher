# MovieSearcher API

영화 검색 및 추천 API 서비스

## 기술 스택

- **Backend**: Spring Boot 3.2.3, Kotlin, JPA
- **Database**: PostgreSQL 15
- **Cache**: Redis 7
- **Security**: Spring Security, JWT
- **Documentation**: Swagger/OpenAPI
- **Monitoring**: Spring Boot Actuator, Prometheus

## 개발 환경 설정

### 1. 필수 요구사항

- Java 17
- Docker & Docker Compose
- Gradle

### 2. 데이터베이스 및 Redis 실행

```bash
# PostgreSQL과 Redis 컨테이너 실행
docker-compose up -d

# 컨테이너 상태 확인
docker-compose ps

# 로그 확인
docker-compose logs -f
```

### 3. 애플리케이션 실행

```bash
# 개발 환경으로 실행
./gradlew bootRun --args='--spring.profiles.active=dev'

# 또는 빌드 후 실행
./gradlew build
java -jar build/libs/movie-searcher-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

### 4. API 문서 확인

- Swagger UI: http://localhost:8080/swagger-ui.html
- Actuator: http://localhost:8080/actuator

## 데이터베이스 스키마

### 주요 테이블

1. **users** - 사용자 정보
2. **user_favorites** - 사용자 즐겨찾기
3. **user_view_history** - 시청 기록
4. **movie_recommendations** - 영화 추천

### 마이그레이션

Flyway를 사용하여 데이터베이스 스키마를 관리합니다:

```bash
# 마이그레이션 상태 확인
./gradlew flywayInfo

# 마이그레이션 실행
./gradlew flywayMigrate
```

## API 엔드포인트

### 인증 API
- `POST /api/v1/auth/signup` - 회원가입
- `POST /api/v1/auth/signin` - 로그인
- `POST /api/v1/auth/refresh` - 토큰 갱신
- `GET /api/v1/auth/profile` - 사용자 프로필 조회

### 영화 API
- `GET /api/v1/movies/search` - 영화 검색
- `GET /api/v1/movies/{id}` - 영화 상세 조회
- `GET /api/v1/movies/search/advanced` - 고급 검색

### 즐겨찾기 API
- `POST /api/v1/favorites` - 즐겨찾기 추가
- `DELETE /api/v1/favorites/{movieId}` - 즐겨찾기 삭제
- `GET /api/v1/favorites` - 즐겨찾기 목록 조회

### 추천 API
- `GET /api/v1/recommendations/personal` - 개인 맞춤 추천
- `GET /api/v1/recommendations/similar/{movieId}` - 유사 영화 추천

## 환경 변수

### 개발 환경 (application-dev.yml)
- 데이터베이스: `moviesearcher_dev`
- Redis: 로컬 Redis
- 로깅: DEBUG 레벨

### 운영 환경 (application-prod.yml)
- 데이터베이스: 운영 DB
- Redis: 운영 Redis
- 로깅: INFO 레벨

## 모니터링

### Actuator 엔드포인트
- `/actuator/health` - 헬스 체크
- `/actuator/metrics` - 메트릭 정보
- `/actuator/prometheus` - Prometheus 메트릭

## 배포

### Docker 이미지 빌드
```bash
docker build -t moviesearcher:latest .
```

### EC2 배포
```bash
# EC2에서 실행
docker run -d \
  --name moviesearcher \
  -p 8080:8080 \
  --env-file .env \
  moviesearcher:latest
``` 