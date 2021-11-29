package syso.syso.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import syso.syso.auth.PrincipalDetails;
import syso.syso.dto.ItemDto;
import syso.syso.entity.Item;
import syso.syso.entity.Member;
import syso.syso.repository.ItemRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;


    public Long 상품등록(ItemDto itemDto, Member member){
        Item item= new Item();
        item.setItemName(itemDto.getItemName());
        item.setPrice(itemDto.getPrice());
        item.setStockNumber(itemDto.getStockNumber());
        item.setMember(member);

        itemRepository.save(item);

        return item.getItemId();
    }

    public String 상품조회(Long itemid){
       Item findItem=itemRepository.findById(itemid).orElseThrow(EntityNotFoundException::new);
       return findItem.getItemName();
    }

    public Item 상품수정(Long itemId, ItemDto itemdto){
        Item updateItem=itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
        updateItem.setItemName(itemdto.getItemName());
        updateItem.setPrice(itemdto.getPrice());
        updateItem.setStockNumber(itemdto.getStockNumber());
//        itemRepository.save(updateItem);

        return updateItem;
        //상품상태
    }

    public void 상품삭제(Long itemid){
        itemRepository.delete(itemRepository.findById(itemid).orElseThrow(EntityNotFoundException::new));

    }



}
