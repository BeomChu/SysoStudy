package syso.syso.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import syso.syso.auth.PrincipalDetails;
import syso.syso.dto.CMRespDto;
import syso.syso.dto.CartDto;
import syso.syso.dto.CartItemDto;
import syso.syso.service.CartService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart/{itemId}")
    public String cartAdd(@PathVariable Long itemId, int cnt, @AuthenticationPrincipal PrincipalDetails principalDetails){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setItemId(itemId);
        cartItemDto.setCnt(cnt);
        cartService.cartItemAdd(principalDetails.getMember(),cartItemDto);

        return "ok";
    }

    @GetMapping("/cart")
    public ResponseEntity<?> getCart(@AuthenticationPrincipal PrincipalDetails principalDetails){
        CartDto cart = cartService.getCart(principalDetails.getMember());

        return new ResponseEntity<>(new CMRespDto<>(1,"장바구니 물건",cart),HttpStatus.OK);
    }
}
