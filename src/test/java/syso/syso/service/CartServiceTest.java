package syso.syso.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import syso.syso.dto.CartItemDto;
import syso.syso.entity.*;
import syso.syso.repository.*;

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

    @Autowired
    private OrderRepository orderRepository;

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

    @Test
    @DisplayName("장바구니취소 테스트")
    public void 장바구니취소테스트(){
        Item item = createItem();
        Member member = createMember();
        itemRepository.save(item);
        memberRepository.save(member);

        Item item1 = createItem();
        itemRepository.save(item1);


        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setItemId(item1.getItemId());
        cartItemDto.setCnt(3);

        Long cartId = cartService.cartItemAdd(member, cartItemDto);
        Long cartId1 = cartService.cartItemAdd(member,cartItemDto);
        
        cartService.cartDelete(cartId,item1.getItemId());
        //CartItem byItem = cartItemRepository.findByItem(item);

        Cart cart = cartRepository.findById(cartId).orElseThrow(EntityNotFoundException::new);
        System.out.println(cart.getCartItems().size());
        assertEquals(cart.getCartItems().size(),1);

    }

    @Test
    @DisplayName("장바구니 상품 주문테스트")
    public void 장바구니상품주문테스트(){
        Item item = createItem();
        Item item1 = createItem();
        Member member = createMember();
        memberRepository.save(member);

        itemRepository.save(item);
        itemRepository.save(item1);

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setItemId(item.getItemId());
        cartItemDto.setCnt(3);

        CartItemDto cartItemDto1 = new CartItemDto();
        cartItemDto1.setItemId(item1.getItemId());
        cartItemDto1.setCnt(3);

        Long cartId = cartService.cartItemAdd(member, cartItemDto);
        Long cartId2 = cartService.cartItemAdd(member, cartItemDto1);

        cartService.orderCart(cartId,member,0);

        PageRequest pageable = PageRequest.of(0,3);

        List<Order> orders = orderRepository.findByOrders(member.getId(), pageable);

        assertEquals(orders.size(),1);
        assertEquals(orders.get(0).getOrderItems().size(),2);
        assertEquals(orders.get(0).getOrderItems().get(0).getItem().getItemId(),item.getItemId());

    }
}