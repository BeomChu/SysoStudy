package syso.syso.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import syso.syso.dto.SignupDto;
import syso.syso.entity.Member;
import syso.syso.repository.MemberRepository;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional


class MemberServiceTest {

    @Test
    @Autowired
    public void 회원가입테스트(){

    }



    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    public static SignupDto createMember(){

        SignupDto signupDto = new SignupDto();

        signupDto.setUserId("asd");
        signupDto.setPassword("asd");
        signupDto.setAddress("asd");
        signupDto.setNicName("asd");
        signupDto.setEmail("asd");

        return signupDto;
    }

    @Test
    @DisplayName("회원가입 되나요?")
    public void 회원가입(){
        SignupDto signupDto = createMember();
        memberService.회원가입(signupDto);

        Member findMember = memberRepository.findByUserId(signupDto.getUserId());
        System.out.println(findMember.getUserId());
        System.out.println("==================");
        System.out.println(signupDto.getUserId());
        assertEquals(findMember.getUserId(),signupDto.getUserId());
    }


}