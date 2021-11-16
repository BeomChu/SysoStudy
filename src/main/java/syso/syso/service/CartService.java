package syso.syso.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syso.syso.dto.CartDetailDto;
import syso.syso.dto.CartDto;
import syso.syso.dto.CartItemDto;
import syso.syso.entity.Cart;
import syso.syso.entity.CartItem;
import syso.syso.entity.Item;
import syso.syso.entity.Member;
import syso.syso.handler.CustomException;
import syso.syso.repository.CartItemRepository;
import syso.syso.repository.CartRepository;
import syso.syso.repository.ItemRepository;
import syso.syso.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;


    // 상품 상세페이지에서 아이템을 장바구니에 담을 수 있음
    // 장바구니에서 수정을 누르면 상품 상세페이지로 연결 결국 기본 로직이 같아짐
    // cartItemRepository에 cartId와 itemId로 조회했을 때 존재하면 값만 수정해주면 됨
    // 상세페이지로 가지 않더라도 controller에서 cartItemAdd 메소드를 사용하면 됩니다.
    public Long cartItemAdd(Member member, CartItemDto cartItemDto){

        Cart cart = cartRepository.findByMember(member);

        if(cart == null){
            Cart cart1 = new Cart();
            cart1.setMember(member);
            cart = cart1;
            cartRepository.save(cart);
        }

        Item item = itemRepository.findById(cartItemDto.getItemId()).orElseThrow(() -> {
            throw new CustomException("아이템이 없습니다.");
        });

        CartItem cartItem = cartItemRepository.findByCartIdAndItem(cart.getId(), item);
        if(cartItem != null){ // 이미 존재
            cartItem.setCnt(cartItemDto.getCnt());
        }else{ // 처음 장바구니에 담음
            CartItem cartItem1 = new CartItem();
            cartItem1.setItem(item);
            cartItem1.setCnt(cartItemDto.getCnt());
            cartItem1.setCart(cart);
            cartItem = cartItem1;
            cartItemRepository.save(cartItem);
        }

        return cart.getId();
    }

    @Transactional(readOnly = true)
    public CartDto getCart(Member member){
        Cart cart = cartRepository.findByMember(member);
        List<CartDetailDto> cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getId());
        CartDto cartDto = new CartDto();
        cartDto.setCartDetailDtoList(cartDetailDtoList);
        int totalPrice = 0;
        for (CartDetailDto cartDetailDto : cartDetailDtoList) {
            int cntPrice = cartDetailDto.getCnt() * cartDetailDto.getPrice();
            totalPrice += cntPrice;
        }
        cartDto.setTotalPrice(totalPrice);

        return cartDto;
    }

}
