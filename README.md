## 스프링 부트와 AWS로 구현하는 웹 서비스 [![Build Status](https://travis-ci.org/ReasonH/Spring-Boot.svg?branch=master)](https://travis-ci.org/ReasonH/Spring-Boot)

---
[스프링 부트와 AWS로 혼자 구현하는 웹 서비스](https://github.com/jojoldu/freelec-springboot2-webservice) 책을 통해 만들어보는 게시판 서비스


개발환경은 다음과 같습니다.

로컬 개발 환경

- IDE: IntelliJ
- Spring-Boot: 2.1.7
- Gradle: 4.10.2
- JAVA: jdk1.8
- DB: H2
- Test: Junit4
- 기타: JPA, Travis

Cloud 환경
- EC2, Amazon Linux 1 AMI, Nginx 설치
- RDS, MariaDB

**목차(구현 내용 및 과정)**
1. 인텔리제이 환경 설정
2. 스프링 부트 테스트 코드 소개
3. 스프링 부트 JPA 소개
4. 머스테치 화면 구성
5. 스프링 시큐리티 OAuth 로그인 구현
6. AWS 서버 환경 구축 - EC2
7. AWS DB 환경 구축 - RDS
8. 서버 배포
9. Travis CI 배포 자동화
10. Nginx를 사용한 무중단 배포

### 9. Travis CI 배포 자동화

- CI: VCS에 push가 되면 자동으로 테스트 및 빌드 수행, 안정적 배포 파일 생성하는 과정
    - 가장 중요한 것은 **완전한 상태를 보장하기 위한 테스트 자동화**
- CD: 빌드 결과를 자동으로 운영서버에 무중단 배포

### 10. Nginx를 사용한 무중단 배포

하나의 EC2 서버에 1대의 Nginx, 2대의 스프링부트 서버 사용

#### 운영 과정
1. 사용자는 서비스 주소로 접속합니다. (80 혹은 433 port)
2. 엔진엑스 사용자의 요청을 받아 현재 연결된 **스프링 부트1(8081)** 로 요청 전달
3. **스프링 부트2(8082)** 는 엔진엑스와 연결되지 않았기 때문에 요청받지 못함
4. 신규 배포시 **스프링 부트2**에 배포
5. 배포가 끝난 후 스프링 부트2의 정상 구동 확인
6. 스프링 부트2가 정상 구동 중이면 Nginx가 8082를 바라보도록 함
7. Nginx 리로드는 0.1초 이내로 완료됨

#### 수정 사항

- nginx 설치 및 `etc/nginx/nginx.conf` 셋팅
- 무중단 배포 위한 profile 추가 및 controller, test, config 작성
- 

#### 무중단 배포 구성 스크립트

- `stop.sh`: 실행 중이던 스프링 부트 종료
- `start.sh`: 배포할 신규 버전 스프링 부트 프로젝트를 `stop.sh`로 종료한 'profile'로 실행
- `health.sh`: `start.sh`로 실행시킨 프로젝트가 정상 실행됐는지 체크
- `switch.sh`: 엔진엑스가 바라보는 스프링 부트를 최신 버전으로 변경
- `profile.sh`: 앞선 4개 스크립트 파일에서 공용으로 사용할 'profile'과 체크 로직

