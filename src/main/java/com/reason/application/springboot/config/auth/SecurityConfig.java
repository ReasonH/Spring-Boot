package com.reason.application.springboot.config.auth;

import com.reason.application.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests() // 권한 관리 옵션 시작
                    .antMatchers("/", "/css/**", "/images/**", // 권한 관리 대상 - 다음의 대상은 permitall
                        "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // api/v1 (post요청)에는 권한 요구
                    .anyRequest().authenticated() // 나머지 url에 대해서는 인증된 사용자 (로그인 사용자)만 허용
                .and()
                    .logout()
                        .logoutSuccessUrl("/") // 로그아웃 성공시 리다이렉트
                .and()
                    .oauth2Login()
                        .userInfoEndpoint() // 사용자 정보 가져오는 설정
                            .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치 시행
    }
}
