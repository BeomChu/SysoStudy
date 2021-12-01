package syso.syso.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import syso.syso.auth.PrincipalDetails;
import syso.syso.dto.*;
import syso.syso.entity.Center;
import syso.syso.service.CenterService;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;

    // 처음 고객센터로 들어갔을 때 문의사항들을 불러오는 컨트롤러
    @GetMapping({"/centerlist", "/centerlist/{page}"})
    public Page<CenterListDto> getCenters(@PathVariable("page") Optional<Integer> page, @AuthenticationPrincipal PrincipalDetails principalDetails){
        PageRequest pageable = PageRequest.of(page.orElse(0),3);

        Page<CenterListDto> centerDtos = centerService.getCenterList(pageable);

        return centerDtos;
    }

    // 한 개를 클릭했을 때 제목과 내용과 글쓴이를 볼 수 있게하는 컨트롤러
    @GetMapping("/center/{centerId}")
    public ResponseEntity<?> getCenter(@PathVariable Long centerId){

        CenterOneDto centerOneDto = centerService.getCenter(centerId);

        return new ResponseEntity<>(new CMRespDto<>(1,"고객 문의 한개",centerOneDto),HttpStatus.OK);
    }


    // 고객 문의 등록하는 컨트롤러
    @PostMapping("/center")
    public ResponseEntity<?> center(@AuthenticationPrincipal PrincipalDetails principalDetails, CenterDto centerDto){
        centerService.createCenter(centerDto, principalDetails.getMember());

        return new ResponseEntity<>(new CMRespDto<>(1,"고객 센터 문의 성공", null),HttpStatus.OK);
    }

    // 문의 내용을 삭제하는 컨트롤러 , 프론트에서 세션을 비교한 후 삭제 권리가 있는지 확인해야함 만약 안되면 서버 내부에서도 가능하게 해야함
    @DeleteMapping("/center/{centerId}")
    public ResponseEntity<?> centerDelete(@PathVariable Long centerId){
        centerService.deleteCenter(centerId);
        return new ResponseEntity<>(new CMRespDto<>(1,"문의 삭제 완료",null),HttpStatus.OK);
    }

    // 문의 내용을 수정하는 컨트롤러 이것도 마찬가지로 세션을 비교하고 수정 권리가 있는지 확인해야함
    @PostMapping("/center/{centerId}")
    public ResponseEntity<?> centerUpdate(@PathVariable Long centerId,@RequestParam String content){

        Center center = centerService.updateCenter(centerId,content);

        return new ResponseEntity<>(new CMRespDto<>(1,"수정 완료",center),HttpStatus.OK);
    }
}
