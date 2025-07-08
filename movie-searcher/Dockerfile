# ==================================================
# 1단계: 빌드(Build) 환경 - 소스코드로 JAR 파일을 만듭니다.
# ==================================================
FROM gradle:8.5-jdk17-alpine AS builder

WORKDIR /app

# build.gradle.kts 와 gradlew 스크립트 먼저 복사
COPY build.gradle.kts gradlew ./

# gradle wrapper 디렉토리 복사
COPY gradle ./gradle

# 소스코드 전체 복사
COPY src ./src

# Gradle을 사용해 실행 가능한 JAR 파일로 빌드합니다.
# 이 단계에서 만들어진 JAR 파일 안에는 application.yml도 포함됩니다.
RUN ./gradlew bootJar --no-daemon


# ==================================================
# 2단계: 실행(Runtime) 환경 - 완성된 JAR 파일을 실행만 합니다.
# ==================================================
# 빌드 환경보다 훨씬 가벼운 이미지를 사용합니다.
FROM openjdk:17-jdk-alpine

WORKDIR /app

# 빌드 단계(builder)에서 만들어진 JAR 파일만 가져와서 복사합니다.
COPY --from=builder /app/build/libs/*.jar app.jar

# 8080 포트를 외부에 노출합니다.
EXPOSE 8080

# 애플리케이션을 실행합니다.
ENTRYPOINT ["java", "-jar", "app.jar"]