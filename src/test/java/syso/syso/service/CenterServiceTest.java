package syso.syso.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import syso.syso.dto.CenterDto;
import syso.syso.entity.Center;
import syso.syso.entity.Member;
import syso.syso.repository.CenterRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CenterServiceTest {


    @Autowired
    private CenterService centerService;

    @Autowired
    private CenterRepository centerRepository;

    public static Member createMember(){
        Member member = new Member();
        member.setEmail("aaa");
        member.setPassword("aaa");
        member.setNicName("aaa");
        member.setAddress("aaa");
        member.setUserId("aaaa");

        return member;
    }

    @Test
    @DisplayName("고객센터 문의 생성")
    public void 고객센터문의생성(){
        Member member = createMember();
        CenterDto centerDto = new CenterDto();
        centerDto.setContent("asd");
        centerDto.setTitle("asd");
        Long centerId = centerService.createCenter(centerDto, member);

        Center center = centerRepository.findById(centerId).get();

        assertEquals(center.getContent(),"asd");
    }
}