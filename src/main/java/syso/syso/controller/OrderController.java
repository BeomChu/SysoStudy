package syso.syso.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import syso.syso.auth.PrincipalDetails;
import syso.syso.dto.OrderDto;
import syso.syso.service.OrderService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/{itemId}")
    public String order(@Valid OrderDto orderDto, BindingResult bindingResult, @PathVariable Long itemId, @AuthenticationPrincipal PrincipalDetails principalDetails){

        if(bindingResult.hasErrors()){
            return "에러 존재";
        }

        orderService.order(itemId,orderDto,principalDetails.getMember());
        return "ok";
    }

    @DeleteMapping("/order/{orderId}")
    public String orderCancel(@PathVariable Long orderId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        orderService.orderCancel(orderId);
        return null;
    }
}
