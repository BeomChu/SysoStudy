package syso.syso.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import syso.syso.dto.OrderDto;
import syso.syso.entity.Item;
import syso.syso.entity.Member;
import syso.syso.entity.Order;
import syso.syso.entity.OrderItem;
import syso.syso.repository.ItemRepository;
import syso.syso.repository.OrderItemRepository;
import syso.syso.repository.OrderRepository;

import javax.persistence.EntityNotFoundException;

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
}