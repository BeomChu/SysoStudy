package syso.syso.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import syso.syso.auth.PrincipalDetails;
import syso.syso.dto.OrderDto;
import syso.syso.dto.OrderHisDto;
import syso.syso.service.OrderService;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

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

    @GetMapping({"/orderList","/orderList/{page}"})
    public Page<OrderHisDto> getOrders(@PathVariable("page") Optional<Integer> page, @AuthenticationPrincipal PrincipalDetails principalDetails){
        PageRequest pageable = PageRequest.of(page.orElse(0),3);

        Page<OrderHisDto> orderHisDtos = orderService.getOrderList(principalDetails.getMember(),pageable);
        return orderHisDtos;
    }


}
