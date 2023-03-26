package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 어노테이션을 사용 => 컨트롤러라고 알리면서 스프링 컨테이너에 올림
public class MemberController {
   // private final MemberService memberService = new MemberService();
   // 객체를 new로 만들면 각각 다른 객체가 만들어짐! => 스프링 컨테이너에 등록해서 사용하기
   // 하나의 객체를 생성하고 공용으로 하나를 사용하면 됨
   private MemberService memberService;
//   @Autowired //setxxx - setter 주입, punlic으로 노출 가능성 => 누구나 바꿀 수 있다
//   public void setMemberService(MemberService memberService) {
//      this.memberService = memberService;
//   }
//    @Autowired private MemberService memberService; // 필드 주입 - 힘듦

   @Autowired // 연결시키는 역활 - 생성자 의존성 주입
   public MemberController(MemberService memberService) { // 스프링 컨테이너의 memberService에 연결
      this.memberService = memberService;
   }

   @GetMapping("/members/new") // get 방식 - 페이지 이동
   public String createForm(){
      return "members/createMemberForm";
   }
   @PostMapping("/members/new") //post 방식
   public String create(MemberForm form){
      Member member = new Member();
      member.setName(form.getName()); // form에서 받은 값을 넘긴다는 의미

      System.out.println("member = "+ member.getName());


      memberService.join(member);

      return "redirect:/"; // 회원 가입이 끝나면 홈 화면으로 돌려보냄
   }

   @GetMapping("/members")
   public String list(Model model){
      List<Member> members = memberService.findMembers(); //findMembers를 하면 모든 멤버를 가져옴
      model.addAttribute("members", members); // 리스트를 담아서 view로 넘길예정, 키와 값을 넣음
      return "members/memberList";
   }
} // end of class