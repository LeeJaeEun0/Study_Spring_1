package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

@Controller
public class HomeController {
    @GetMapping ("/")
    public String home(){ // 화면 시작하면 보이는 페이지
        return "home";
    }



} // end of HomeController
