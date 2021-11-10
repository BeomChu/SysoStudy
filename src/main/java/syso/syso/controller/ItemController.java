package syso.syso.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import syso.syso.auth.PrincipalDetails;
import syso.syso.dto.ItemDto;
import syso.syso.service.ItemService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/newitem")
    public List<String> newItem(@Valid ItemDto itemDto, BindingResult bindingResult, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){

        List<String> err=new ArrayList<>();

        if(bindingResult.hasErrors()){
            for(FieldError error : bindingResult.getFieldErrors()){

                err.add(error.getDefaultMessage());
                System.out.println(error.getDefaultMessage());
            }
            return err;
        }

        try{
            itemService.상품등록(itemDto,principalDetails.getMember());
        }catch (Exception e){
            err.add(e.getMessage());
            System.out.println(e.getMessage());
        }
        return err;


    }

}
