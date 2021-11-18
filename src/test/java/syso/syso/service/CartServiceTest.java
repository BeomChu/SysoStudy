package syso.syso.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import syso.syso.dto.CartItemDto;
import syso.syso.entity.Cart;
import syso.syso.entity.CartItem;
import syso.syso.entity.Item;
import syso.syso.entity.Member;
import syso.syso.repository.CartItemRepository;
import syso.syso.repository.CartRepository;
import syso.syso.repository.ItemRepository;
import syso.syso.repository.MemberRepository;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartServiceTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    public void init(){

    }

    public static Item createItem(){
        Item item = new Item();
        item.setStockNumber(100);
        item.setItemName("테스트상품");
        item.setPrice(10000);

        return item;
    }

    public static Member createMember(){
        Member member = new Member();
        member.setEmail("aaa");
        member.setPassword("aaa");
        member.setNicName("aaa");
        member.setAddress("aaa");
        member.setUserId("aaaa");

        return member;
    }

    @Test
    @DisplayName("장바구니 물건 넣기 테스트")
    public void 장바구니물건넣기(){
        Item item = createItem();
        itemRepository.save(item);
        Member member = createMember();
        memberRepository.save(member);
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setItemId(item.getItemId());
        cartItemDto.setCnt(3);


        Long cartId = cartService.cartItemAdd(member, cartItemDto);

        Cart cart = cartRepository.findById(cartId).orElseThrow(EntityNotFoundException::new);
        assertEquals(cart.getMember().getId(),member.getId());

        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);

        assertEquals(cartItems.size(),1);
        assertEquals(cartItems.get(0).getCnt(),3);


        cartItemDto.setCnt(6);
        cartService.cartItemAdd(member,cartItemDto);
        assertEquals(cartItems.size(),1);
        assertEquals(cartItems.get(0).getCnt(),6);
    }
}