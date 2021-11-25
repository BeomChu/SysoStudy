package syso.syso.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import syso.syso.auth.PrincipalDetails;
import syso.syso.dto.UserDto;
import syso.syso.service.ProfileService;

@RestController
@RequiredArgsConstructor //service 가져오기
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/profile/{id}")
    public UserDto profile(@PathVariable Long id){ //id를 사용하기위해서는 @PathVariable을 꼭 사용

        return profileService.개인프로필(id);
    }

    @PutMapping("/profile/{id}/update")
    public void update(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails,UserDto userDto){
        profileService.회원정보수정(id,userDto);
    }
}
