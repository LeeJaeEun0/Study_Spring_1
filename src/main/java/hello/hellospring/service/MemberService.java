package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.reopsitory.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service // 서비스로 스프링컨테이너에 저장
//@Component
public class MemberService {
    // MemberService와 MemberServiceTest가 가르키는 레파지토리가 다름
    //private final MemberRepository memberRepository = new MemoryMemberReopsitory();
    // MemberService와 Test에서 사용하는 레파지토리가 다름(다른 인스턴스)=> 코드를 수정
    private final MemberRepository memberRepository; // 회원서비스를 제공하기 위해서 레파지토리 사용함
//    @Autowired //생성자 호출
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    * 회원 가입
    * */
    public Long join(Member member) {
        vaildateDuplicateMember(member); // 같은 이름의 중복 회원 검증 => 중복은 불가능
        memberRepository.save(member);
        return member.getId();
    } // end of join()

    private void vaildateDuplicateMember(Member member) {
        // 같은 이름이 있는 중복 회원X
        memberRepository.findByName(member.getName())
                .ifPresent(n -> {
                // ifPresent null이 아닌값이 있으면 동작하는 메서드
                    // 같은 이름의 회원이 있음 -> 오류 발생
                    throw  new IllegalStateException("이미 존재하는 회원입니다.");
                });
    } // end of vaildateDuplicateMember()

    /*
        전체 회원 조회
    */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    } // end of findMemebers()

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    } // end of findOne()

} // end of class
