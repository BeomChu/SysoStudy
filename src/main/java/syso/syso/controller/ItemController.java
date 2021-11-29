package syso.syso.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import syso.syso.auth.PrincipalDetails;
import syso.syso.dto.ItemDto;
import syso.syso.entity.Item;
import syso.syso.repository.ItemRepository;
import syso.syso.service.ItemService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @PostMapping("/newitem")
    public List<String> newItem(@Valid ItemDto itemDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails){

        List<String> err=new ArrayList<>();

        if(bindingResult.hasErrors()){
            for(FieldError error : bindingResult.getFieldErrors()){

                err.add(error.getDefaultMessage());
                System.out.println(error.getDefaultMessage());
            }

        }else {
            itemService.상품등록(itemDto, principalDetails.getMember());
        }


        return err;


    }

    @GetMapping("/checkitem/{itemId}")
    public String checkItem(ItemDto itemDto,@PathVariable Long itemId){
        String item=itemService.상품조회(itemId);

        return item;
    }

    @PutMapping("/updateitem/{itemId}")
    public String updateItem(@PathVariable Long itemId, @Valid ItemDto itemDto, BindingResult bindingResult,@AuthenticationPrincipal PrincipalDetails principalDetails){
        itemService.상품수정(itemId,itemDto);

        return "실행됨";
    }

    @GetMapping("/findItem/{page}")
    public Page<Item> findItemByPage(@PathVariable("page") int page){
        PageRequest pageRequest=PageRequest.of(page,3);

        return itemRepository.findAll(pageRequest);

    }

}
