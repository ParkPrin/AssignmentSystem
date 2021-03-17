# AssignmentSystem
AssignmentSystem Example Project

## 사용하고 있는 라이브러리 정리

## 프로젝트 환경구성

- 패키징 관리툴: Gradle
- 웹 프레임워크: Spring boot
- 데이터베이스: PostgreSQL

### DB 실행
실행하기 전 Docker 설치가 되어있어야 한다. 
```
1.docker-compose.yml 만들기: docker-compose.example 내용을 복사후
docker-compose.yml 파일을 만들고 내용붙여넣기, 
한글로 작성된 부분만 자신의 조건에 맞추어 입력한다. 

2. 도커로 DATABASE 실행하기
$ docker-compose -f docker/database/docker-compose.yml up
```

## 초기데이터 세팅

웹서비스를 실행후 브라우저에서 아래 URL을 실행하면 초기데이터가 DB 세팅이 된다.
```
http://localhost:5000/api/initdata
```

## 테스트 코드 실행

## Swagger
http://localhost:5000/assignment/swagger-ui/index.html#/





