package syso.syso.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syso.syso.dto.UserDto;
import syso.syso.entity.Member;
import syso.syso.repository.MemberRepository;

import javax.persistence.EntityNotFoundException;


@Service //Getter,Setter 등을 사용 service 항목에는 거의다 들어간다고 보면 된다.
@RequiredArgsConstructor //의존성 주입의 편의성을 위해서 사용
@Transactional //추가,갱신,삭제 등을 처리할때 오류가 발생하면 모든작업을 원상태로 돌릴 수 있다.
public class ProfileService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder; //비밀번호 암호화


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
    //회원정보수정(ProfileUpdate)
    @Transactional
    public Member 회원정보수정(Long userId,UserDto userDto){

        //객체를 꺼내온다.
        Member memberEntity=memberRepository.findById(userId).get();


        //회원정보 수정
        if(userDto.getAddress() != null) {
            memberEntity.setAddress(userDto.getAddress());
        }
        if(userDto.getEmail() != null) {
            memberEntity.setEmail((userDto.getEmail()));
        }
        if(userDto.getNicName() != null) {
            memberEntity.setNicName(userDto.getNicName());
        }

        return memberEntity;
    }
}
