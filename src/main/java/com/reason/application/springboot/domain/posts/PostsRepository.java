package com.reason.application.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


// 기본 CRUD를 자동 생성해준다, Entity와 Entity Repository는 항시 동일 패키지에 위치한다.
public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id desc")
    List<Posts> findAllDesc();
}
