package com.reason.application.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 어노테이션 생성 가능 위치 지정, 메소드 파라미터로 선언된 객체에서만 사용 가능
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { // LoginUser 라는 annotation 생성
}
