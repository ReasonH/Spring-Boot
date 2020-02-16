package com.reason.application.springboot.config.auth;

import com.reason.application.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    // 조건에 맞는 경우 메소드가 있다면 HMAR의 구현체가 지정한 값으로
    // 해당 메소드 파라미터값으로 넘길 수 있음
    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter
                                     parameter) {
        // 컨트롤러 메소드의 특정 파라미터 지원하는지 판단
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation
                (LoginUser.class) != null; // 파라미터 어노테이션 판단
        boolean isUserClass = SessionUser.class. // 클래스타입이 SessionUser.class 인지 판단
                equals(parameter.getParameterType());
        return isLoginUserAnnotation && isUserClass;
    }

    @Override // 파라미터에 전달할 객체 생성, 여기서는 세션에서 객체 획득
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws
            Exception {
        return httpSession.getAttribute("user");
    }
}
