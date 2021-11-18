package syso.syso.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import syso.syso.constant.OrderStatus;
import syso.syso.dto.OrderDto;
import syso.syso.dto.OrderHisDto;
import syso.syso.entity.Item;
import syso.syso.entity.Member;
import syso.syso.entity.Order;
import syso.syso.entity.OrderItem;
import syso.syso.handler.CustomException;
import syso.syso.repository.ItemRepository;
import syso.syso.repository.OrderItemRepository;
import syso.syso.repository.OrderRepository;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Test
    @DisplayName("order 테스트")
    public void 주문테스트(){
        Item item = new Item();
        item.setItemName("테스트아이템");
        item.setStockNumber(100);
        item.setPrice(1000);

        itemRepository.save(item);

        Member member = new Member();

        OrderDto orderDto = new OrderDto();
        orderDto.setCnt(5);

        Long orderItemId = orderService.order(item.getItemId(), orderDto, member);


        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(EntityNotFoundException::new);
        assertEquals(orderItem.getItem(),item);
        assertEquals(orderItem.getCount(), orderDto.getCnt());
    }

    @Test
    @DisplayName("포인트를 이용한 주문 테스트")
    public void 포인트주문(){
        Item item = new Item();
        item.setItemName("테스트아이템");
        item.setStockNumber(100);
        item.setPrice(1000);

        itemRepository.save(item);

        Member member = new Member();
        member.setPoint(10000);

        OrderDto orderDto = new OrderDto();
        orderDto.setCnt(2);
        orderDto.setPoint(4000);

        CustomException customException = assertThrows(CustomException.class, () -> {
            orderService.order(item.getItemId(), orderDto, member);
        });

        assertEquals(customException.getMessage(),"포인트를 너무 많이 사용했습니다.");

        // 위의 테스트는 2개만 사서 사용한 포인트가 구매할 가격보다 높은 경우
        // 아래 테스트는 10개를 사서 사용한 포인트가 구매할 가격보다 적은 경우
        orderDto.setCnt(10);

        Long orderId = orderService.order(item.getItemId(), orderDto, member);

        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);

        assertEquals(member.getPoint(),6000);
    }

    @Test
    @DisplayName("주문 취소 테스트")
    public void 주문취소테스트(){
        Item item = new Item();
        item.setItemName("테스트아이템");
        item.setStockNumber(100);
        item.setPrice(1000);

        itemRepository.save(item);

        Member member = new Member();
        member.setPoint(10000);

        OrderDto orderDto = new OrderDto();
        orderDto.setCnt(10);
        orderDto.setPoint(4000);

        Long orderId = orderService.order(item.getItemId(), orderDto, member);

        orderService.orderCancel(orderId);

        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);

        assertEquals(order.getOrderStatus(), OrderStatus.CANCEL);

    }

    @Test
    @DisplayName("주문 내역 조회하기")
    public void 주문내역조회(){

        PageRequest pageable = PageRequest.of(0,3);

        Item item = new Item();
        item.setItemName("테스트아이템");
        item.setStockNumber(100);
        item.setPrice(1000);

        itemRepository.save(item);

        OrderDto orderDto = new OrderDto();
        orderDto.setCnt(10);

        Member member = new Member();
        member.setUserId("aaaa");
        member.setAddress("aaa");
        member.setPassword("aaa");
        member.setNicName("aaa");
        member.setEmail("aaa");
        member.setPoint(10000);

        orderService.order(item.getItemId(), orderDto, member);

        for(int i = 0; i<3;i++){
            orderService.order(item.getItemId(), orderDto, member);
        }

        Page<OrderHisDto> orderList = orderService.getOrderList(member, pageable);
        long totalElements = orderList.getTotalElements();
        assertEquals(totalElements,3);
        System.out.println(orderList.getSize());
    }
}