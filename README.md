## 스프링 부트와 AWS로 구현하는 웹 서비스

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
- EC2, Amazon Linux 1 AMI
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

### 9. Travis CI 배포 자동화

- CI: VCS에 push가 되면 자동으로 테스트 및 빌드 수행, 안정적 배포 파일 생성하는 과정
    - 가장 중요한 것은 **완전한 상태를 보장하기 위한 테스트 자동화**
- CD: 빌드 결과를 자동으로 운영서버에 무중단 배포
