package com.reason.application.springboot.web;
import com.reason.application.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON 컨트롤러로 만든다
public class HelloController {

    @GetMapping("/hello") // Get 요청 API 생성
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String
                                        name,
                            @RequestParam("amount") int
                                    amount) {
        return new HelloResponseDto(name, amount);
    }
}