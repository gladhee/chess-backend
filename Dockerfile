# Stage 1: Build
FROM openjdk:21-jdk-bullseye AS build
WORKDIR /home/gradle/project
# 전체 소스 복사 (gradlew, app 폴더 등 모두 포함)
COPY . .

# gradlew에 실행 권한 부여 후, app 모듈 빌드
# :app:bootJar 태스크를 실행하여 app/build/libs 폴더에 JAR 파일을 생성
RUN chmod +x ./gradlew && \
    ./gradlew :app:clean :app:bootJar --no-daemon && \
    ls -l app/build/libs/

WORKDIR /app
# 빌드 단계에서 생성된 JAR 파일을 복사 (실제 JAR 파일 이름에 맞게 와일드카드를 사용)
COPY --from=build /home/gradle/project/app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]