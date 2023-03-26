package hello.hellospring;

import hello.hellospring.reopsitory.JdbcMemberRepository;
import hello.hellospring.reopsitory.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private DataSource dataSource; // application.properties의 내용을 보고 빈을 생성

    public SpringConfig(DataSource dataSource) { // 주입
        this.dataSource = dataSource;
    }

    @Bean // 스프링 빈에 저장하라는 뜻, 스프링이 실행되면 스프링 컨테이너에 저장
    public MemberService memberService(){ // 생성자 주입
        return  new MemberService(memberRepository()); 
        // 생성자라서 값이 필요 - 원래 MemberService 생성자에 값을 넣기때문
        // memberRepository() 호출
    }

    @Bean 
    public MemberRepository memberRepository(){

        return new JdbcMemberRepository(dataSource);
    }
} // end of class
