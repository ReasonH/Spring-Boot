package com.reason.application.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication // 스프링 부트 자동 설정, Bean읽기 생성 자동화
public class Application { // 메인 클래스 - 이 클래스는 항상 프로젝트의 최상단에 위치해야함
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); // 내장 WAS실행
    }
}
