package hello.hellospring.reopsitory;
import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원 저장 + 반환
    Optional<Member> findById(Long id); // 아이디로 찾기  //Optional로 아이디로 찾은 결과를 반환
    Optional<Member> findByName(String name);
    List<Member> findAll(); // 저장된 모든 회원을 리스트 반환

} // end of interface
