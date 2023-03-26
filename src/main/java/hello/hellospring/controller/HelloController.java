package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    } // end of hello()

    @GetMapping("hello-mvc")
    public String helloWC(@RequestParam(value="name") String name, Model model) { // 외부에서 값을 받아올 예정
        model.addAttribute("name",name); //외부에서 받아온 값을 키와 name
        return "hello-template";
    } // end of helloWC

    @GetMapping("hello-string")
    @ResponseBody // http의 body부에 직접 값을 넣겠다는 의미, html의 body가 아님
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // view 없이 그대로 보임
    } // end of helloString

    @GetMapping("hello-api")
    @ResponseBody // @ResponseBody와 객체를 사용하면 JSON 방식으로 보면되고 많이 사용되는 방식
    public Hello helloApi(@RequestParam("name")String name){
        // 결과가 JSON 방식으로 출력
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    } // end of helloApi
    static class Hello{ // 클래스 안에 클래스를 만들기 위해서 static으로 만듦
        private String name;

        public String getName() {
            return name;
        } //getter

        public void setName(String name) {
            this.name = name;
        } // setter
    } // end of class
} // end of class
