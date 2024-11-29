# RentalMS
- 프로젝트 생성
  - https://start.spring.io/
- 패키지 구조 생성
- 도메인 모델 개발
  - 엔티티,VO 생성
  - 도메인 모델 테스트
- 응용 서비스 개발
- 외부 영역 개발
  - 인바운드 영역
    - REST API
    - DTO
  - 아웃바운드 영역
    - 레파지토리
    - OR 매핑
- 도커 파일 생성
  - Dockerfile 생성
  - 이미 존재함
    ```
    # 기반이 될 도커 이미지 선택 (여기서는 OpenJDK 11 사용)
    FROM openjdk:15-jdk-alpine
  
    # 빌드된 스프링 부트 JAR 파일을 복사
    COPY target/*SNAPSHOT.jar app.jar
  
    # 컨테이너가 실행될 포트 지정
    EXPOSE 8080
    ENV TZ=Asia/Seoul
    RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
    ENTRYPOINT ["java","-Xmx400M","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=docker"]
    ```

- 빌드
  - 어플리케이션 빌드
  
    ```
    mvn clean package 
    ```

- 컨테이너 생성
  - 도커 컨테이너 이미지 생성
  
    ```
     docker build -t rental-ms:0.0.1 . 
    ```

    ```
    docker image 
    ```

  - 도커 컨테이너 실행

    ``` 
    docker run --name rentalMS -p 8080:8080 rental-ms:0.0.1
    ```

