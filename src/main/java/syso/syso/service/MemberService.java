package syso.syso.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syso.syso.dto.SignupDto;
import syso.syso.entity.Member;
import syso.syso.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void 회원가입(SignupDto signupDto){
        checkDuplication(signupDto.getUserId());

        Member member = new Member();
        member.setUserId(signupDto.getUserId());

        String nPassword = bCryptPasswordEncoder.encode(signupDto.getPassword());
        member.setPassword(nPassword);
        member.setEmail(signupDto.getEmail());
        member.setAddress(signupDto.getAddress());
        member.setNicName(signupDto.getNicName());

        memberRepository.save(member);
    }

    public void checkDuplication(String userId){
        Member member = memberRepository.findByUserId(userId);

        if(member != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
