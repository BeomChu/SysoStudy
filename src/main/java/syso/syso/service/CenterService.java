package syso.syso.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syso.syso.dto.CenterDto;
import syso.syso.dto.CenterListDto;
import syso.syso.dto.CenterOneDto;
import syso.syso.dto.OrderHisDto;
import syso.syso.entity.Center;
import syso.syso.entity.Member;
import syso.syso.handler.CustomException;
import syso.syso.repository.CenterRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CenterService {

    private final CenterRepository centerRepository;

    // 고객센터 문의 생성
    public Long createCenter(CenterDto centerDto, Member member){
        Center center = Center.createCenter(centerDto.getTitle(), centerDto.getContent(),member);
        centerRepository.save(center);

        return center.getId();
    }

    public Long deleteCenter(Long centerId){

        Center center = centerRepository.findById(centerId).orElseThrow(() -> {
            throw new CustomException("내용을 찾을 수 없습니다.");
        });
        centerRepository.delete(center);

        return center.getId();
    }

    public Center updateCenter(Long centerId,String content){
        Center center = centerRepository.findById(centerId).orElseThrow(() -> {
            throw new CustomException("값을 찾을 수 없습니다.");
        });

        center.setContent(content);

        return center;
    }

    @Transactional(readOnly = true)
    public Page<CenterListDto> getCenterList(Pageable pageable){
        List<CenterListDto> centerListDtos = centerRepository.findByCenters(pageable);

        Long totalCount = centerRepository.countOrder();

        return new PageImpl<CenterListDto>(centerListDtos,pageable,totalCount);
    }

    @Transactional(readOnly = true)
    public CenterOneDto getCenter(Long centerId){

        CenterOneDto centerOneDto = centerRepository.findByCenterOneDto(centerId);

        return centerOneDto;
    }
}
