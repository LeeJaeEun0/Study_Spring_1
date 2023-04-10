package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.reopsitory.MemberRepository;
import hello.hellospring.reopsitory.MemoryMemberReopsitory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryMemberReopsitoryTest {
    MemoryMemberReopsitory repository = new MemoryMemberReopsitory();


    @AfterEach
    public void afterEach() {
        repository.clearStore(); // 테스트 끝나고 저장소 지움
    } // 메서드가 끝날때마다 호출되는 메서드, 콜백 메서드처럼 사용

    @Test
    public void save(){ // 저장 기능 테스트
        Member member = new Member();
        member.setName("spring"); // 이름값을 넣음

        repository.save(member); // repository에 값을 저장 - id가 세팅

        Member result = repository.findById(member.getId()).get(); // 값을 꺼내서 result에 넣음 // 넣은 값이 잘 들어갔는지 확인
        // System.out.println("result = " +(result == member)); // 결과가 같은지 확인 - 권장 안함
        //  Assertions.assertEquals(member, result); // 기대하는 값과 결과가 일치하는지
        //Assertions.assertThat(member).isEqualTo(result); // member과 result가 같은지 비교
        assertThat(member).isEqualTo(result); // 값이 같은지 확인핳
    } // end of save

    @Test
    public void findByName(){
        // member1 객체 생성
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1); // 멤버 저장

        // member2 객체 생성
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").orElse(member1);
        assertThat(result).isEqualTo(member2);
    } // end of findByName

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

       List<Member> result = repository.findAll();
       assertThat(result.size()).isEqualTo(2); // 사이즈 확인 - 총 몇개 있는지
    } // end of findAll
} // end of class
