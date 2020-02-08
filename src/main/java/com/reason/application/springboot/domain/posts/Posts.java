package com.reason.application.springboot.domain.posts;

import com.reason.application.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 모든 필드 getter 자동 생성
@NoArgsConstructor // 기본 생성자 자동 추가 public Posts(){} 와 동일
@Entity // 클래스가 테이블과 링크됨을 나타낸다. 클래스명 -> 테이블명 매칭
public class Posts extends BaseTimeEntity {
    @Id // PK가 됨
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙
    private Long id;
    // 엔티티 pk는 auto increment가 좋다
    // 주민번호, 복합키 등은 유니크 키로 별도 설정

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    // 어노테이션 컬럼은 명시하지 않아도 되지만
    // 기본값을 수정해야 하는 경우 사용한다. ex) varchar size / type 등

    private String author;

    @Builder // 생성자 포함 필드만 빌더에 포함, 빌더 패턴 생성(중요)
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

/*
    롬복 어노테이션은 entity 클래스 변경 발생 시
    코드 변경을 최소화시킨다.
 */