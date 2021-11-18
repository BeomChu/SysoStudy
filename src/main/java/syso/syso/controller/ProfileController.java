package syso.syso.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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
}
