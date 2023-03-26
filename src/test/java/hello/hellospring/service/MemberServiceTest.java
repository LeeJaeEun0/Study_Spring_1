package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.reopsitory.MemoryMemberReopsitory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.* ;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberReopsitory memberReopsitory;
    @BeforeEach
    public void beforeEach(){
        memberReopsitory = new MemoryMemberReopsitory();
        memberService = new MemberService(memberReopsitory);
    } // 테스트를 실행하기전에 동작

    @AfterEach
    public void afterEach() {
        memberReopsitory.clearStore(); // 테스트 끝나고 저장소 지움
    } // 메서드가 끝날때마다 호출되는 메서드, 콜백 메서드처럼 사용

    @Test
    void 회원가입() {
        // given : 주어진 값이 있을때
        Member member = new Member();
        member.setName("spring"); // spring이 두명이기 때문에 오류 발생

        // when : 이것을 실행했을때
        Long saveId = memberService.join(member);

        // then : 결과가 어떻게 나와야하는지
        Member findMemeber  = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMemeber.getName());
    } // end of 회원가입
    @Test
    public void 중복_회원_예외(){ //예외 사항 - 중복 회원 검증 로직이 잘 돌아가는 지 확인
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/


        // then
    } // end of 중복_회원_예외

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}