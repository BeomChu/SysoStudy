package syso.syso.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import syso.syso.auth.PrincipalDetails;
import syso.syso.dto.ItemDto;
import syso.syso.entity.Item;
import syso.syso.handler.CustomValidationException;
import syso.syso.repository.ItemRepository;
import syso.syso.service.ItemService;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @PostMapping("/createitem")
    public String createItem(@Valid ItemDto itemDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails){

        Map<String,String> err=new HashMap<>();

        if(bindingResult.hasErrors()){
            for(FieldError error : bindingResult.getFieldErrors()){

                err.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패",err);

        }else {
            itemService.createItem(itemDto, principalDetails.getMember());
        }


        return "아이템 등록 성공";


    }

    @GetMapping("/finditem/{itemId}")
    public String findItem(ItemDto itemDto,@PathVariable Long itemId){
        String item=itemService.findItem(itemId);

        return item;
    }

    @PutMapping("/updateitem/{itemId}")
    public String updateItem(@PathVariable Long itemId, @Valid ItemDto itemDto, BindingResult bindingResult,@AuthenticationPrincipal PrincipalDetails principalDetails){
        Map<String,String> err=new HashMap<>();

        if(bindingResult.hasErrors()){
            for(FieldError error : bindingResult.getFieldErrors()){

                err.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패",err);

        }else {
            itemService.updateItem(itemId,itemDto);
        }


        return "상품 수정 성공";
    }

    @GetMapping("/finditem/{page}")
    public Page<Item> findItemByPage(@PathVariable("page") int page){
        PageRequest pageRequest=PageRequest.of(page,3);

        return itemRepository.findAll(pageRequest);

    }

    @DeleteMapping("/deleteitem/{itemId}")
    public String deleteItem(@PathVariable Long itemId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        itemService.deleteItemByItemId(itemId,principalDetails.getMember());

        return "상품 삭제 성공";
    }

}
