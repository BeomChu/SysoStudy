package syso.syso.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syso.syso.dto.UserDto;
import syso.syso.entity.Member;
import syso.syso.repository.MemberRepository;

import javax.persistence.EntityNotFoundException;


@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true) //읽기만 할 수 있게하는 기능
    public UserDto 개인프로필(Long id){
        Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        UserDto userDto = new UserDto();

        userDto.setUserId(member.getUserId());
        userDto.setNicName(member.getNicName());
        userDto.setAddress(member.getAddress());
        userDto.setPoint(member.getPoint());
        userDto.setEmail(member.getEmail());

        return userDto;
    }
}
