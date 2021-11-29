package syso.syso.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import syso.syso.dto.ItemDto;
import syso.syso.entity.Item;
import syso.syso.entity.Member;
import syso.syso.repository.ItemRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemRepository itemRepository;

    public static ItemDto createItem(){
        ItemDto item = new ItemDto();
        item.setStockNumber(100);
        item.setPrice(10000);
        item.setItemName("테스트아이템");
        return item;

    }

    @Test
    @DisplayName("상품등록테스트")
    public void 상품등록테스트(){
        Member member = new Member();
        ItemDto item = createItem();
        Long itemId = itemService.상품등록(item, member);
        Item findItem = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);

//        assertEquals(findItem.getItemName() , item.getItemName());
    }
    @Test
    @DisplayName("상품조회테스트")
    public void 상품조회테스트(){
        Member member = new Member();
        ItemDto item = createItem();
        Long itemId = itemService.상품등록(item, member);
        String 상품조회 = itemService.상품조회(itemId);
        assertEquals(상품조회,item.getItemName());
    }
    @Test
    @DisplayName("상품수정테스트")
    public void 상품수정테스트() {
        Member member = new Member();
        ItemDto item = createItem();
        Long itemId = itemService.상품등록(item, member);
        ItemDto testitem= new ItemDto();
        testitem.setItemName("변경된이름");
        testitem.setPrice(5000);
        testitem.setStockNumber(300);
        itemService.상품수정(itemId,testitem);


        assertEquals(itemService.상품조회(itemId),testitem.getItemName());
    }
    @Test
    @DisplayName("상품삭제테스트")
    public void 상품삭제테스트() {
        Member member = new Member();
        ItemDto item = createItem();
        Long itemId = itemService.상품등록(item, member);
        itemService.상품삭제(itemId);


        assertThrows(EntityNotFoundException.class,()->{
            itemService.상품조회(itemId);
        });
    }



}