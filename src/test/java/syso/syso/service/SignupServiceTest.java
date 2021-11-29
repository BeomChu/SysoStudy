package syso.syso.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import syso.syso.dto.SignupDto;
import syso.syso.repository.MemberRepository;

@SpringBootTest
@Transactional
public class SignupServiceTest {

    @Autowired
    public MemberRepository memberRepository;

    @Autowired
    public MemberService memberService;

    @Test
    @DisplayName("회원가입테스트")
    public void 회원가입테스트(){
        SignupDto signupDto=new SignupDto();
        signupDto.setAddress("주소");
        signupDto.setEmail("이메일");

    }


}
