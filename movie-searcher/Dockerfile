# 1. OpenJDK 17 기반 이미지 사용
FROM openjdk:17-jdk-alpine

# 2. JAR 파일을 컨테이너로 복사
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 3. 포트 오픈 (Spring Boot 기본 8080)
EXPOSE 8080

# 4. 실행 명령
COPY src/main/resources/application.yml /application.yml
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.config.location=file:/application.yml"]