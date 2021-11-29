package syso.syso.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syso.syso.auth.PrincipalDetails;
import syso.syso.constant.Grade;
import syso.syso.constant.Role;
import syso.syso.dto.SignupDto;
import syso.syso.entity.Member;
import syso.syso.handler.CustomException;
import syso.syso.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;

    public void 회원가입(SignupDto signupDto){
        checkDuplication(signupDto.getUserId());

        String nPassword = bCryptPasswordEncoder.encode(signupDto.getPassword());

        Member member = Member.createMember(signupDto.getUserId(), nPassword, signupDto.getNicName(), signupDto.getAddress());
        member.setRole(Role.USER);

        memberRepository.save(member);
    }

    public void checkDuplication(String userId){
        Member member = memberRepository.findByUserId(userId);

        if(member != null){
            throw new CustomException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member findMember = memberRepository.findByUserId(userId);

        if(findMember == null){
            throw new UsernameNotFoundException(userId);
        }

        return new PrincipalDetails(findMember);
    }

    public void findPassword(String userId){
        Member member = memberRepository.findByUserId(userId);
        String newPassword = emailService.mailCheck(member.getEmail());
        String nPassword = bCryptPasswordEncoder.encode(newPassword);
        member.setPassword(nPassword);
    }
}
