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
import syso.syso.handler.CustomException;
import syso.syso.repository.ItemRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;


    public Long createItem(ItemDto itemDto, Member member){
        Item item= Item.createItem(itemDto.getItemName(),itemDto.getPrice(),itemDto.getStockNumber(),member);
        itemRepository.save(item);

        return item.getItemId();
    }

    //판매자 이름,
    public String findItem(Long itemId){
       Item findItem=itemRepository.findById(itemId).orElseThrow(()->{
           throw new CustomException("상품을 찾을 수 없습니다.");
       });
       return findItem.getItemName();
    }

    public Long updateItem(Long itemId, ItemDto itemdto){
        Item updateItem=itemRepository.findById(itemId).orElseThrow(()->{
            throw new CustomException("상풍을 찾을 수 없습니다.");
        });
        updateItem.setItemName(itemdto.getItemName());
        updateItem.setPrice(itemdto.getPrice());
        updateItem.setStockNumber(itemdto.getStockNumber());
//        itemRepository.save(updateItem);

        return updateItem.getItemId();
        //상품상태
    }

    public void deleteItemByItemId(Long itemId,Member member){

        Item dItem = itemRepository.findById(itemId).orElseThrow(()->{
            throw new CustomException("상품을 찾을 수 없습니다.");
        });
        if(dItem.getMember()==member){
            itemRepository.delete(dItem);
        }else{
            throw new CustomException("판매자만 삭제할 수 있습니다.");
        }


    }



}
