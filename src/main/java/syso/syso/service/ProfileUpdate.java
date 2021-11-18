package syso.syso.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import syso.syso.dto.SignupDto;
import syso.syso.dto.UserDto;
import syso.syso.entity.Member;
import syso.syso.repository.MemberRepository;


@Service
@RequiredArgsConstructor

public class ProfileUpdate {

    private  final MemberRepository memberRepository;
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Member 회원정보수정(Long id,UserDto userDto){

        //객체를 꺼내온다.
        Member memberEntity=memberRepository.findById(id).get();


        //회원정보 수정

        memberEntity.setAddress(userDto.getAddress());
        memberEntity.setEmail((userDto.getEmail()));
        memberEntity.setNicName(userDto.getNicName());

        return memberEntity;


    }
}
