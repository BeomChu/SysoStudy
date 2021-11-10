package syso.syso.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import syso.syso.dto.ItemDto;
import syso.syso.entity.Item;
import syso.syso.entity.Member;
import syso.syso.repository.ItemRepository;

@Service
@AllArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;


    public void 상품등록(ItemDto itemDto, Member member){
        Item item= new Item();
        item.setItemName(itemDto.getItemName());
        item.setPrice(itemDto.getPrice());
        item.setStockNumber(itemDto.getStockNumber());
        item.setMember(member);

        itemRepository.save(item);
    }
}
