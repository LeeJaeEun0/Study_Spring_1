package hello.hellospring.reopsitory;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
//@Repository
public class MemoryMemberReopsitory implements MemberRepository{ //오버라이딩 ctrl + o

    private static Map<Long, Member> store = new HashMap<>(); //<키, 값>
    private static long sequence = 0L; // 키 값을 생성

//    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 값을 넣고
        store.put(member.getId(), member); // 값을 꺼냄
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // 결과 값이 null일경우 optional로 감싸서 나옴
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 람다 사용 - 반복하면서 찾음// id의 결과가 일치하는지를
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store의 멤버를 반환
    }

    public void clearStore(){
        store.clear();
    }
} // end of class
