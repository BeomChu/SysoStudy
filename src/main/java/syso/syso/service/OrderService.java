package syso.syso.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public Long order(Long itemId, OrderDto orderDto, Member member){
        Item findItem = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);

        if(findItem.getStockNumber() < orderDto.getCnt()){
            throw new IllegalStateException("개수가 초과했습니다.");
        }
        Order order = new Order();
        order.setMember(member);
        orderRepository.save(order);

        findItem.setStockNumber(findItem.getStockNumber()-orderDto.getCnt());
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(findItem);
        orderItem.setOrder(order);
        orderItem.setCount(orderDto.getCnt());
        orderItem.setOrderPrice(orderDto.getCnt() * findItem.getPrice());
        orderItemRepository.save(orderItem);

        return orderItem.getId();
    }
}
