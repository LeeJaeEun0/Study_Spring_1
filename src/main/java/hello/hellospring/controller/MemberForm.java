package hello.hellospring.controller;

public class MemberForm {
    private String name; // createMemberForm.html과 매치해서 값을 받아옴

    public String getName() { // 이름을 빼냄
        return name;
    }

    public void setName(String name) { // 이름을 넣음
        this.name = name;
    }
} // end of class
