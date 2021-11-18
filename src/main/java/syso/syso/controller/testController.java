package syso.syso.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import syso.syso.dto.SignupDto;
import syso.syso.handler.CustomValidationException;
import syso.syso.repository.MemberRepository;
import syso.syso.service.MemberService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class testController {

    private final MemberService memberService;

    @GetMapping("/new")
    public String getSignUp(Model model){
        SignupDto signupDto = new SignupDto();
        model.addAttribute("signUpDto", new SignupDto());
        return "member/signup";
    }

    @PostMapping("/new1")
    public String signUp(@Valid SignupDto signupDto, BindingResult bindingResult, Model model){

        Map<String,String> err = new HashMap<>();

        if(bindingResult.hasErrors()){
            for(FieldError error : bindingResult.getFieldErrors()){

                err.put(error.getField(),error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패함",err);
        }

        memberService.회원가입(signupDto);

        return "/member/signin";

    }

}
